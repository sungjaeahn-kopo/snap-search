package com.snap_search.lvalue.service;

import java.util.List;

import com.snap_search.lvalue.dto.MatchEventDTO;

public interface MatchEventService {
	List<MatchEventDTO> getEventsByFixtureId(Long fixtureId);
}
