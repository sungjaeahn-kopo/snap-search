package com.snap_search.lvalue.service;

import java.util.Optional;

import com.snap_search.lvalue.dto.CoachDTO;

public interface CoachService {

	Optional<CoachDTO> getCoachByTeamId(Long teamId);
}
