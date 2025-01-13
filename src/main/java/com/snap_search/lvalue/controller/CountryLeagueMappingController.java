package com.snap_search.lvalue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.dto.CountryLeagueMappingDTO;
import com.snap_search.lvalue.service.CountryLeagueMappingService;

@RestController
@RequestMapping("/api/mapping")
public class CountryLeagueMappingController {

	private final CountryLeagueMappingService countryLeagueMappingService;

	public CountryLeagueMappingController(CountryLeagueMappingService countryLeagueMappingService) {
		this.countryLeagueMappingService = countryLeagueMappingService;
	}

	/**
	 * 시즌 선택후 국가 리스트 반환 메서드
	 * @param season
	 * @return CountryLeagueMappingDTO
	 */
	@GetMapping("/countryInfo")
	public List<CountryLeagueMappingDTO> getCountries(@RequestParam("season") String season) {
		return countryLeagueMappingService.getCountiesBySeason(season);
	}

	/**
	 * 시즌, 국가 선택후 리그 리스트 반환 메서드
	 * @param season, countryId
	 * @return CountryLeagueMappingDTO
	 */
	@GetMapping("/leagueInfo")
	public List<CountryLeagueMappingDTO> getLeagues(@RequestParam("season") String season,
		@RequestParam("countryId") Long countryId) {
		return countryLeagueMappingService.getLeaguesBySeasonAndCountry(season, countryId);
	}

	/**
	 * 시즌, 국가, 리그 선택후 팀 리스트 반환 메서드
	 * @param season, leagueId, countryId
	 * @return CountryLeagueMappingDTO
	 */
	@GetMapping("/teamInfo")
	public List<CountryLeagueMappingDTO> getTeams(@RequestParam("season") String season,
		@RequestParam("countryId") Long countryId, @RequestParam("leagueId") Long leagueId) {
		return countryLeagueMappingService.getLeaguesBySeasonAndCountryAndLeague(season, countryId, leagueId);
	}
}
