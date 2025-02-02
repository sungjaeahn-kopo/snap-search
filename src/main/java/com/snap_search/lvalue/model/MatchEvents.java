package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fixture_events")
public class MatchEvents {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // 개별 이벤트의 고유 ID 추가

	private Long fixtureId;
	private int timeElapsed;
	private int timeExtra;
	private int teamId;
	private String teamName;
	private String teamLogo;
	private int playerId;
	private String playerName;
	private Integer assistId;
	private String assistName;
	private String eventType;
	private String detail;
	private String comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Long getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(Long fixtureId) {
		this.fixtureId = fixtureId;
	}

	public int getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(int timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public int getTimeExtra() {
		return timeExtra;
	}

	public void setTimeExtra(int timeExtra) {
		this.timeExtra = timeExtra;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamLogo() {
		return teamLogo;
	}

	public void setTeamLogo(String teamLogo) {
		this.teamLogo = teamLogo;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getAssistId() {
		return assistId;
	}

	public void setAssistId(Integer assistId) {
		this.assistId = assistId;
	}

	public String getAssistName() {
		return assistName;
	}

	public void setAssistName(String assistName) {
		this.assistName = assistName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
