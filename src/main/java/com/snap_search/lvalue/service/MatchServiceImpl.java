package com.snap_search.lvalue.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiFixture;
import com.snap_search.lvalue.api.ApiMatch;
import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.model.Match;
import com.snap_search.lvalue.repository.MatchRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MatchServiceImpl implements MatchService {

	private final WebClient webClient;
	private final MatchRepository matchRepository;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public MatchServiceImpl(@Qualifier("webClientForDefault") WebClient webClient, MatchRepository matchRepository) {
		this.webClient = webClient;
		this.matchRepository = matchRepository;
	}

	@Override
	public List<Match> getMatchesByTeam(int teamId, int season) {
		return matchRepository.findByLeagueSeasonAndTeamsHomeIdOrLeagueSeasonAndTeamsAwayId(season, teamId, season,
			teamId);
	}

	@Override
	public Optional<Match> getMatchById(Long fixtureId) {
		return matchRepository.findById(String.valueOf(fixtureId));
	}

	@Override
	public Optional<Match> getUpcomingMatchByTeam(int teamId, int season) {
		// 현재 한국 시간 (KST) 가져오기
		LocalDateTime nowKst = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

		// 날짜 포맷팅 (DB 조회 시 비교 가능하도록 변환)
		String nowKstStr = nowKst.format(FORMATTER);

		// 다가오는 경기 리스트 조회 (오름차순 정렬됨)
		List<Match> upcomingMatches = matchRepository.findUpcomingMatches(teamId, season, nowKstStr);

		// 가장 가까운 경기 반환 (없으면 Optional.empty())
		return upcomingMatches.stream().findFirst();
	}

	/**
	 * 더미 데이터 중 아직 진행되지 않은 현재 날짜 이전까지의 fixture_id에 대해 data fetch
	 * @return
	 */
	@Override
	public List<Match> fetchMatcheData(Long leagueId, int teamId) {
		LocalDateTime now = LocalDateTime.now();
		// DB에서 더미 데이터 중 현재 시간 이전까지 진행되지 않은 경기 목록 조회
		List<Integer> upcomingFixtureIds = matchRepository.findUpcomingFixtureIds(now.format(FORMATTER), leagueId,
			teamId);

		if (upcomingFixtureIds.isEmpty()) {
			return List.of(); // 호출할 fixture_id가 없으면 빈 리스트 반환
		}

		// 각 fixture_id별로 개별 API 호출 후 병렬 처리 (비동기)
		List<Match> updatedMatches = Flux.fromIterable(upcomingFixtureIds)
			.delayElements(Duration.ofMillis(100))
			.flatMap(this::fetchFixtureById, 5) // 각 fixture_id에 대해 API 호출
			.flatMapSequential(Mono::justOrEmpty)
			.collectList()
			.block(); // 블록킹 방식으로 동기 변환

		// DB에 저장 및 반환
		if (updatedMatches != null && !updatedMatches.isEmpty()) {
			return matchRepository.saveAll(updatedMatches);
		}

		return List.of();
	}

	/**
	 * 특정 fixture_id에 대한 경기 데이터를 API로 조회
	 * @param fixtureId - 경기 ID
	 * @return Mono<Match> - 변환된 Match 객체
	 */
	private Mono<Match> fetchFixtureById(Integer fixtureId) {
		System.out.println("🟢 Requesting fixtureId: " + fixtureId);

		return webClient.get()
			.uri(uriBuilder -> uriBuilder.path("/fixtures").queryParam("id", fixtureId).build())
			.exchangeToMono(response -> {
				System.out.println("🟡 Received HTTP Status: " + response.statusCode());

				if (response.statusCode().is2xxSuccessful()) {
					return response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiFixture>>() {
						})
						.doOnNext(apiResponse -> System.out.println("🟡 API Response: " + apiResponse));
				} else {
					return response.createException()
						.flatMap(Mono::error);
				}
			})
			.timeout(Duration.ofSeconds(10)) // 10초 타임아웃
			.flatMap(apiResponse -> Mono.justOrEmpty(apiResponse.getResponse().stream()
				.map(this::convertToDBFormat)
				.findFirst()))
			.doOnError(error -> System.err.println("❌ Error for fixtureId " + fixtureId + ": " + error.getMessage()))
			.doOnSuccess(match -> System.out.println("✅ Converted match: " + match));
	}

	private Match convertToDBFormat(ApiFixture apiFixture) {
		Match match = new Match();

		if (apiFixture.getMatch() != null) {
			ApiMatch apiMatch = apiFixture.getMatch();

			// 기본 정보 (Fixture ID, 경기 날짜, 상태)
			match.setFixtureId(apiMatch.getFixtureId());
			match.setFixtureDate(apiMatch.getFixtureDate()); // 경기 날짜
			match.setFixtureStatusShort(apiMatch.getStatus().getFixtureStatusShort()); // 경기 상태

			// 리그 정보
			if (apiFixture.getLeague() != null) {
				match.setLeagueName(apiFixture.getLeague().getLeagueName());
				match.setLeagueSeason(Math.toIntExact(apiFixture.getLeague().getLeagueSeason()));
				match.setLeagueRound(apiFixture.getLeague().getLeagueRound());
			}

			// 홈 팀 정보
			if (apiFixture.getTeams() != null) {
				match.setTeamsHomeId(Math.toIntExact(apiFixture.getTeams().getTeamsHomeId()));
				match.setTeamsHomeName(apiFixture.getTeams().getTeamsHomeName());
				match.setTeamsHomeLogo(apiFixture.getTeams().getTeamsHomeLogo());
				match.setTeamsAwayId(Math.toIntExact(apiFixture.getTeams().getTeamsAwayId()));
				match.setTeamsAwayName(apiFixture.getTeams().getTeamsAwayName());
				match.setTeamsAwayLogo(apiFixture.getTeams().getTeamsAwayLogo());
			}

			// 경기 결과 (골 정보)
			if (apiFixture.getGoals() != null) {
				match.setGoalsHome(
					apiFixture.getGoals().getGoalsHome() != null ? apiFixture.getGoals().getGoalsHome().intValue() :
						null);
				match.setGoalsAway(
					apiFixture.getGoals().getGoalsAway() != null ? apiFixture.getGoals().getGoalsAway().intValue() :
						null);
			}

			// 하프타임 및 풀타임 스코어
			if (apiFixture.getScore() != null) {
				match.setScoreHalftimeHome(Math.toIntExact(apiFixture.getScore().getHalfTime().getHome()));
				match.setScoreHalftimeAway(Math.toIntExact(apiFixture.getScore().getHalfTime().getAway()));
				match.setScoreFulltimeHome(Math.toIntExact(apiFixture.getScore().getFullTime().getHome()));
				match.setScoreFulltimeAway(Math.toIntExact(apiFixture.getScore().getFullTime().getAway()));
			}

			// 경기장 정보 (ApiVenue 활용)
			if (apiMatch.getVenue() != null) {
				match.setVenueId(apiMatch.getVenue().getFixtureVenueId().toString());
				match.setVenueName(apiMatch.getVenue().getFixtureVenueName());
				match.setVenueCity(apiMatch.getVenue().getFixtureVenueCity());
			}
		}

		return match;
	}

}
