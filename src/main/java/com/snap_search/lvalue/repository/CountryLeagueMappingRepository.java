package com.snap_search.lvalue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snap_search.lvalue.model.CountryLeagueMap;

public interface CountryLeagueMappingRepository extends JpaRepository<CountryLeagueMap, String> {

	@Query("""
		    SELECT new CountryLeagueMap(ltm.leagueSeason, l.country.id, c.name, c.flag)
		    FROM CountryLeagueMap ltm
		    JOIN League l ON ltm.leagueId = l.id
			JOIN Country c ON l.country.id = c.id
		    WHERE 1=1 
		    AND ltm.leagueSeason = :season
		    GROUP BY ltm.leagueSeason, l.country.id, c.name, c.flag
		""")
	List<CountryLeagueMap> findCountriesBySeason(@Param("season") String season);

	@Query("""
		    SELECT new CountryLeagueMap(ltm.leagueSeason, c.id, c.name, c.flag, l.id, l.name, l.logo)
		    FROM CountryLeagueMap ltm
		    JOIN League l ON ltm.leagueId = l.id
			JOIN Country c ON l.country.id = c.id
		    WHERE 1=1 
		    AND ltm.leagueSeason = :season and c.id = :countryId
		    GROUP BY ltm.leagueSeason, c.id, c.name, c.flag, l.id, l.name, l.logo
		""")
	List<CountryLeagueMap> findLeaguesBySeasonAndCountry(@Param("season") String season,
		@Param("countryId") Long countryId);

	@Query("""
		    SELECT new CountryLeagueMap(ltm.leagueSeason, c.name, c.flag, ltm.teamId, ltm.teamName, ltm.teamLogo)
		    FROM CountryLeagueMap ltm
		    JOIN League l ON ltm.leagueId = l.id
			JOIN Country c ON l.country.id = c.id
		    WHERE 1=1 
		    AND ltm.leagueSeason = :season and c.id = :countryId and ltm.leagueId = :leagueId
		    GROUP BY ltm.leagueSeason, c.name, c.flag, ltm.teamId, ltm.teamName, ltm.teamLogo
		""")
	List<CountryLeagueMap> findLeaguesBySeasonAndCountryAndLeague(@Param("season") String season,
		@Param("countryId") Long countryId, @Param("leagueId") Long leagueId);
}
