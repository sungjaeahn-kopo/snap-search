package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiEvent {
	@JsonProperty("time")
	private ApiEventTime eventTime;
	@JsonProperty("team")
	private ApiEventTeam eventTeam;
	@JsonProperty("player")
	private ApiEventPlayer eventPlayer;
	@JsonProperty("assist")
	private ApiEventAssist eventAssist;

	private String type;
	private String detail;
	private String comments;

	public static class ApiEventTime {
		@JsonProperty("elapsed")
		private Integer eventElapsed;
		@JsonProperty("extra")
		private Integer eventExtra;

		public Integer getEventElapsed() {
			return eventElapsed;
		}

		public void setEventElapsed(Integer eventElapsed) {
			this.eventElapsed = eventElapsed;
		}

		public Integer getEventExtra() {
			return eventExtra;
		}

		public void setEventExtra(Integer eventExtra) {
			this.eventExtra = eventExtra;
		}
	}

	public static class ApiEventTeam {
		@JsonProperty("id")
		private Integer teamId;
		@JsonProperty("name")
		private String teamName;
		@JsonProperty("logo")
		private String teamLogo;

		public Integer getTeamId() {
			return teamId;
		}

		public void setTeamId(Integer teamId) {
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
	}

	public static class ApiEventPlayer {
		@JsonProperty("id")
		private Integer playerId;
		@JsonProperty("name")
		private String playerName;

		public Integer getPlayerId() {
			return playerId;
		}

		public void setPlayerId(Integer playerId) {
			this.playerId = playerId;
		}

		public String getPlayerName() {
			return playerName;
		}

		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}
	}

	public static class ApiEventAssist {
		@JsonProperty("id")
		private Integer assistId;
		@JsonProperty("name")
		private String assistName;

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
	}

	public ApiEventTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(ApiEventTime eventTime) {
		this.eventTime = eventTime;
	}

	public ApiEventTeam getEventTeam() {
		return eventTeam;
	}

	public void setEventTeam(ApiEventTeam eventTeam) {
		this.eventTeam = eventTeam;
	}

	public ApiEventPlayer getEventPlayer() {
		return eventPlayer;
	}

	public void setEventPlayer(ApiEventPlayer eventPlayer) {
		this.eventPlayer = eventPlayer;
	}

	public ApiEventAssist getEventAssist() {
		return eventAssist;
	}

	public void setEventAssist(ApiEventAssist eventAssist) {
		this.eventAssist = eventAssist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
