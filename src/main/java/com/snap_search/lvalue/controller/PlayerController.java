package com.snap_search.lvalue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.dto.TeamWithPlayersDTO;
import com.snap_search.lvalue.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	private final PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping("/fetch")
	public List<TeamWithPlayersDTO> fetchCountries(@RequestParam("teamId") List<Long> teamIds) {
		return playerService.fetchAndSavePlayers(teamIds);
	}

	@GetMapping("/playerInfo")
	public List<TeamWithPlayersDTO> getPlayers(@RequestParam("teamId") Long teamId) {
		return playerService.getPlayers(teamId);
	}
}
