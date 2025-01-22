package com.snap_search.lvalue.dto;

import java.util.List;

public class TeamWithPlayersDTO {

	private List<PlayerDTO> players; // 커리어 리스트 포함
	private Long teamId;

	public TeamWithPlayersDTO(List<PlayerDTO> players, Long teamId) {
		this.players = players;
		this.teamId = teamId;
	}

	public List<PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerDTO> players) {
		this.players = players;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
