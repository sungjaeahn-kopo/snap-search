package com.snap_search.lvalue.service;

import java.util.List;

import com.snap_search.lvalue.dto.CountryLeagueMappingDTO;

public interface CountryLeagueMappingService {

	List<CountryLeagueMappingDTO> getCountiesBySeason(String season);

	List<CountryLeagueMappingDTO> getLeaguesBySeasonAndCountry(String season, Long countryId);

	List<CountryLeagueMappingDTO> getLeaguesBySeasonAndCountryAndLeague(String season, Long countryId, Long leagueId);
}
