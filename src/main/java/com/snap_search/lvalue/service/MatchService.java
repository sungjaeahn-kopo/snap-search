package com.snap_search.lvalue.service;

import java.util.List;
import java.util.Optional;

import com.snap_search.lvalue.model.Match;

public interface MatchService {

	/**
	 * 특정 팀의 전체 경기 일정 조회
	 * @param teamId
	 * @param season
	 * @return
	 */
	List<Match> getMatchesByTeam(int teamId, int season);

	/**
	 * 특정 경기 상세 조회
	 * @param fixtureId
	 * @return
	 */
	Optional<Match> getMatchById(Long fixtureId);

	/**
	 * 특정 팀의 다가오는 경기 조회
	 * @param teamId
	 * @return
	 */
	Optional<Match> getUpcomingMatchByTeam(int teamId, int season);
}
