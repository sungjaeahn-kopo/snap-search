package com.snap_search.lvalue.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiEvent;
import com.snap_search.lvalue.api.ApiFixture;
import com.snap_search.lvalue.api.ApiMatch;
import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.model.Match;
import com.snap_search.lvalue.model.MatchEvents;
import com.snap_search.lvalue.repository.MatchEventRepository;
import com.snap_search.lvalue.repository.MatchRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MatchServiceImpl implements MatchService {

	private final WebClient webClient;
	private final MatchRepository matchRepository;
	private final MatchEventRepository matchEventsRepository;
	private static final Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public MatchServiceImpl(@Qualifier("webClientForDefault") WebClient webClient, MatchRepository matchRepository,
		MatchEventRepository matchEventsRepository) {
		this.webClient = webClient;
		this.matchRepository = matchRepository;
		this.matchEventsRepository = matchEventsRepository;
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
	public List<Match> fetchMatcheData(int teamId) {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
		// DB에서 더미 데이터 중 현재 시간 이전까지 진행되지 않은 경기 목록 조회
//		List<Integer> upcomingFixtureIds = matchRepository.findUpcomingFixtureIds(now.format(FORMATTER), teamId);
//
//		if (upcomingFixtureIds.isEmpty()) {
//			return List.of(); // 호출할 fixture_id가 없으면 빈 리스트 반환
//		}

		 List<Integer> upcomingFixtureIds = new ArrayList<>();
		 Integer[] test = {1299110,
				 1299093,
				 1208180};
		 upcomingFixtureIds.addAll(List.of(test));

		// 각 fixture_id별로 개별 API 호출 후 병렬 처리 (비동기)
		List<Match> updatedMatches = Flux.fromIterable(upcomingFixtureIds)
			.delayElements(Duration.ofMillis(100))
			.flatMap(this::fetchFixtureById, 5) // 각 fixture_id에 대해 API 호출
			.flatMapSequential(Mono::justOrEmpty)
			.collectList()
			.block(); // 블록킹 방식으로 동기 변환

		// DB에 저장 및 반환
		if (updatedMatches != null && !updatedMatches.isEmpty()) {
			logger.info("Saving {} matches into DB", updatedMatches.size());
			return matchRepository.saveAll(updatedMatches);
		}

		logger.warn("No matches fetched for leagueId: {} teamId: {}", teamId);
		return List.of();
	}

	/**
	 * 특정 fixture_id에 대한 경기 데이터를 API로 조회
	 * @param fixtureId - 경기 ID
	 * @return Mono<Match> - 변환된 Match 객체
	 */
	private Mono<Match> fetchFixtureById(Integer fixtureId) {
		return webClient.get()
			.uri(uriBuilder -> uriBuilder.path("/fixtures").queryParam("id", fixtureId).build())
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiFixture>>() {
			})
			.map(ApiResponse::getResponse)
			.filter(responseList -> !responseList.isEmpty()) // 응답이 비어있으면 제거
			.map(responseList -> convertToDBFormat(responseList.get(0))) // 첫 번째 요소만 변환
			.doOnError(error -> logger.error("❌ Error for fixtureId {}: {}", fixtureId, error.getMessage()))
			.doOnSuccess(match -> logger.info("✅ Converted match: {}", match));
	}

	private Match convertToDBFormat(ApiFixture apiFixture) {
		Match match = new Match();

		if (apiFixture.getMatch() != null) {
			ApiMatch apiMatch = apiFixture.getMatch();

			// 기본 정보 (Fixture ID, 경기 날짜, 상태)
			match.setFixtureId(apiMatch.getFixtureId());
			match.setFixtureDate(convertToUTC(apiMatch.getFixtureDate()));
			match.setFixtureReferee(apiMatch.getFixtureReferee());

			// 리그 정보
			if (apiFixture.getLeague() != null) {
				match.setLeagueId(Math.toIntExact(apiFixture.getLeague().getLeagueId()));
				match.setLeagueName(apiFixture.getLeague().getLeagueName());
				match.setLeagueSeason(Math.toIntExact(apiFixture.getLeague().getLeagueSeason()));
				match.setLeagueRound(apiFixture.getLeague().getLeagueRound());
			}

			// 홈 팀 정보
			if (apiFixture.getTeams() != null) {
				match.setTeamsHomeId(Math.toIntExact(apiFixture.getTeams().getHome().getId()));
				match.setTeamsHomeName(apiFixture.getTeams().getHome().getName());
				match.setTeamsHomeLogo(apiFixture.getTeams().getHome().getLogo());
				match.setTeamsHomeWinner(apiFixture.getTeams().getHome().getWinner());

				match.setTeamsAwayId(Math.toIntExact(apiFixture.getTeams().getAway().getId()));
				match.setTeamsAwayName(apiFixture.getTeams().getAway().getName());
				match.setTeamsAwayLogo(apiFixture.getTeams().getAway().getLogo());
				match.setTeamsAwayWinner(apiFixture.getTeams().getAway().getWinner());
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
				match.setFixtureVenueId(Math.toIntExact(apiMatch.getVenue().getFixtureVenueId()));
				match.setFixtureVenueName(apiMatch.getVenue().getFixtureVenueName());
				match.setFixtureVenueCity(apiMatch.getVenue().getFixtureVenueCity());
			}

			// 경기장 정보 (ApiPeriod 활용)
			if (apiMatch.getPeriods() != null) {
				match.setFixturePeriodsFirst(Math.toIntExact(apiMatch.getPeriods().getFixturePeriodsFirst()));
				match.setFixturePeriodsSecond(Math.toIntExact(apiMatch.getPeriods().getFixturePeriodsSecond()));
			}

			// 경기장 정보 (ApiStatus 활용)
			if (apiMatch.getStatus() != null) {
				match.setFixtureStatusLong(apiMatch.getStatus().getFixtureStatusLong());
				match.setFixtureStatusShort(apiMatch.getStatus().getFixtureStatusShort());
				match.setFixtureStatusElapsed(Math.toIntExact(apiMatch.getStatus().getFixtureStatusElapsed()));
				match.setFixtureStatusExtra(Math.toIntExact(apiMatch.getStatus().getFixtureStatusExtra()));
			}

			if (apiFixture.getEvents() != null) {
				// 경기 이벤트 (ApiEvents 활용)
				List<MatchEvents> matchEventsList = apiFixture.getEvents().stream()
					.map(event -> convertToMatchEvent(event, match)) // Match와 연결
					.toList();
				matchEventsRepository.saveAll(matchEventsList);
			}

		}

		return match;
	}

	private MatchEvents convertToMatchEvent(ApiEvent apiEvent, Match match) {
		MatchEvents event = new MatchEvents();

		event.setFixtureId(match.getFixtureId()); // Match와 연관된 fixtureId
		event.setTimeElapsed(apiEvent.getEventTime().getEventElapsed());
		event.setTimeExtra(apiEvent.getEventTime().getEventExtra());

		event.setTeamId(apiEvent.getEventTeam().getTeamId());
		event.setTeamName(apiEvent.getEventTeam().getTeamName());
		event.setTeamLogo(apiEvent.getEventTeam().getTeamLogo());

		event.setPlayerId(apiEvent.getEventPlayer().getPlayerId());
		event.setPlayerName(apiEvent.getEventPlayer().getPlayerName());

		event.setAssistId(apiEvent.getEventAssist().getAssistId());
		event.setAssistName(apiEvent.getEventAssist().getAssistName());

		event.setEventType(apiEvent.getType());
		event.setDetail(apiEvent.getDetail());
		event.setComments(apiEvent.getComments());

		return event;
	}

	private String convertToUTC(String dateTimeString) {
		if (dateTimeString == null || dateTimeString.isBlank())
			return null;

		// ✅ ISO 8601 형식 파싱 후 UTC 변환
		ZonedDateTime utcDateTime = ZonedDateTime.parse(dateTimeString)
			.withZoneSameInstant(ZoneId.of("UTC"));

		// ✅ 원하는 포맷으로 변환 ("yyyy-MM-dd HH:mm:ss UTC")
		return utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'"));
	}

}
