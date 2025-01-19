package com.snap_search.lvalue.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.dto.CoachDTO;
import com.snap_search.lvalue.service.CoachService;

@RestController
@RequestMapping("/api")
public class CoachController {

	private final CoachService coachService;

	public CoachController(CoachService coachService) {
		this.coachService = coachService;
	}

	/**
	 * 시즌 선택후 국가 리스트 반환 메서드
	 * @param teamId
	 * @return CountryLeagueMappingDTO
	 */
	@GetMapping("/coachInfo")
	public Optional<CoachDTO> getCountries(@RequestParam("teamId") Long teamId) {
		return coachService.getCoachByTeamId(teamId);
	}
}
