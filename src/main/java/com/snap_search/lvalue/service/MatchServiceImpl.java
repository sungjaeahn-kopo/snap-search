package com.snap_search.lvalue.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snap_search.lvalue.model.Match;
import com.snap_search.lvalue.repository.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {

	private final MatchRepository matchRepository;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public MatchServiceImpl(MatchRepository matchRepository) {
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

}
