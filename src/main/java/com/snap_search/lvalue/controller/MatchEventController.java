package com.snap_search.lvalue.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.dto.MatchEventDTO;
import com.snap_search.lvalue.service.MatchEventService;

@RestController
@RequestMapping("/api/match-event")
public class MatchEventController {

	private final MatchEventService matchEventService;

	public MatchEventController(MatchEventService matchEventService) {
		this.matchEventService = matchEventService;
	}

	@GetMapping
	public ResponseEntity<List<MatchEventDTO>> fetchMatchData(@RequestParam("fixtureId") Long fixtureId) {
		List<MatchEventDTO> matchEvents = matchEventService.getEventsByFixtureId(fixtureId);
		return ResponseEntity.ok(matchEvents);
	}
}
