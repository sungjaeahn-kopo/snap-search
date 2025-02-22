package com.snap_search.lvalue.dto;

import com.snap_search.lvalue.model.MatchEvents;

public class MatchEventDTO {
	private Long fixtureId;
	private Integer timeElapsed;
	private Integer timeExtra;
	private Integer teamId;
	private String teamName;
	private String teamLogo;
	private Integer playerId;
	private String playerName;
	private Integer assistId;
	private String assistName;
	private String eventType;
	private String detail;
	private String comments;

	public MatchEventDTO(MatchEvents event) {
		this.fixtureId = event.getFixtureId();
		this.timeElapsed = event.getTimeElapsed();
		this.timeExtra = event.getTimeExtra();
		this.teamId = event.getTeamId();
		this.teamName = event.getTeamName();
		this.teamLogo = event.getTeamLogo();
		this.playerId = event.getPlayerId();
		this.playerName = event.getPlayerName();
		this.assistId = event.getAssistId();
		this.assistName = event.getAssistName();
		this.eventType = event.getEventType();
		this.detail = event.getDetail();
		this.comments = event.getComments();
	}

	public static MatchEventDTO fromEntity(MatchEvents event) {
		return new MatchEventDTO(event);
	}

	public Long getFixtureId() {
		return fixtureId;
	}

	public Integer getTimeElapsed() {
		return timeElapsed;
	}

	public Integer getTimeExtra() {
		return timeExtra;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getTeamLogo() {
		return teamLogo;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public Integer getAssistId() {
		return assistId;
	}

	public String getAssistName() {
		return assistName;
	}

	public String getEventType() {
		return eventType;
	}

	public String getDetail() {
		return detail;
	}

	public String getComments() {
		return comments;
	}
}
