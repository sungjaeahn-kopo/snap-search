package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snap_search.lvalue.model.Match;

public interface MatchRepository extends JpaRepository<Match, String> {
	List<Match> findByLeagueSeasonAndTeamsHomeIdOrLeagueSeasonAndTeamsAwayId(int season, int homeId, int seasonAgain,
		int awayId);

	// 다가오는 경기 조회 (fixture_date가 String이므로 날짜 비교를 위한 정렬 수행)
	@Query("""
		SELECT m FROM Match m 
		WHERE 1=1 
		AND m.leagueSeason = :season
		AND (m.teamsHomeId = :teamId OR m.teamsAwayId = :teamId) 
		AND m.fixtureDate > :today 
		ORDER BY m.fixtureDate ASC
		""")
	List<Match> findUpcomingMatches(@Param("teamId") int teamId, @Param("season") int season,
		@Param("today") String today);

	@Query("SELECT m.fixtureId FROM Match m WHERE m.fixtureStatusShort NOT IN ('FT', 'PST') AND (m.teamsHomeId = :teamId OR m.teamsAwayId = :teamId) AND m.leagueId = :leagueId AND m.fixtureDate < :currentDate")
	List<Integer> findUpcomingFixtureIds(@Param("currentDate") String currentDate, @Param("leagueId") Long leagueId,
		@Param("teamId") int teamId);
}
