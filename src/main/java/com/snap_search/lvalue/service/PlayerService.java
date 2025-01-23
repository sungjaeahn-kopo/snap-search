package com.snap_search.lvalue.service;

import java.util.List;

import com.snap_search.lvalue.dto.TeamWithPlayersDTO;

public interface PlayerService {
	List<TeamWithPlayersDTO> fetchAndSavePlayers(List<Long> teamIds);

	List<TeamWithPlayersDTO> getPlayers(Long teamId);
}
