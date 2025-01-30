package com.snap_search.lvalue.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.model.Match;
import com.snap_search.lvalue.service.MatchService;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	/**
	 * 특정 팀의 경기일정 조회
	 * @param teamId
	 * @return ResponseEntity<List < Match>>
	 */
	@GetMapping
	public ResponseEntity<List<Match>> getMatchesByTeam(
		@RequestParam("teamId") int teamId,
		@RequestParam("season") int season
	) {
		List<Match> matches = matchService.getMatchesByTeam(teamId, season);
		return ResponseEntity.ok(matches);
	}
}
