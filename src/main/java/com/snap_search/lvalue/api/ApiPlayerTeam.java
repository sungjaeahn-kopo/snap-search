package com.snap_search.lvalue.api;

import java.util.List;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 */
public class ApiPlayerTeam {
	private ApiTeam team;
	private List<ApiPlayer> players;

	public ApiTeam getTeam() {
		return team;
	}

	public void setTeam(ApiTeam team) {
		this.team = team;
	}

	public List<ApiPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<ApiPlayer> players) {
		this.players = players;
	}
}
