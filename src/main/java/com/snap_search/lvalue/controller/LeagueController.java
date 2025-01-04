package com.snap_search.lvalue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.model.League;
import com.snap_search.lvalue.service.LeagueService;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

	private final LeagueService leagueService;

	public LeagueController(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	@GetMapping("/fetch")
	public List<League> fetchCountries() {
		return leagueService.fetchAndSaveLeagues();
	}
}
