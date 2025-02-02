package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.snap_search.lvalue.dto.MatchEventDTO;
import com.snap_search.lvalue.model.MatchEvents;
import com.snap_search.lvalue.repository.MatchEventRepository;

@Service
public class MatchEventServiceImpl implements MatchEventService {
	private static final Logger logger = LoggerFactory.getLogger(MatchEventServiceImpl.class);
	private final MatchEventRepository matchEventRepository;

	public MatchEventServiceImpl(MatchEventRepository matchEventRepository) {
		this.matchEventRepository = matchEventRepository;
	}

	@Override
	public List<MatchEventDTO> getEventsByFixtureId(Long fixtureId) {
		List<MatchEvents> events = matchEventRepository.findByFixtureIdOrderByTimeElapsed(fixtureId);
		return events.stream().map(MatchEventDTO::fromEntity).collect(Collectors.toList());
	}

}
