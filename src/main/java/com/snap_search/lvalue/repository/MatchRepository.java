package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap_search.lvalue.model.Match;

public interface MatchRepository extends JpaRepository<Match, String> {
	List<Match> findByLeagueSeasonAndTeamsHomeIdOrLeagueSeasonAndTeamsAwayId(int season, int homeId, int seasonAgain,
		int awayId);
}
