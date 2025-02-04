package com.snap_search.lvalue.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiFixture {

	@JsonProperty("fixture")
	private ApiMatch match;

	@JsonProperty("league")
	private ApiLeague.ApiFixtureLeague league;

	@JsonProperty("teams")
	private ApiTeam teams;

	@JsonProperty("goals")
	private ApiGoal goals;

	@JsonProperty("score")
	private ApiScore score;

	@JsonProperty("events")
	private List<ApiEvent> events;

	public static class ApiGoal {
		@JsonProperty("home")
		private Long goalsHome;
		@JsonProperty("away")
		private Long goalsAway;

		public Long getGoalsHome() {
			return goalsHome;
		}

		public void setGoalsHome(Long goalsHome) {
			this.goalsHome = goalsHome;
		}

		public Long getGoalsAway() {
			return goalsAway;
		}

		public void setGoalsAway(Long goalsAway) {
			this.goalsAway = goalsAway;
		}
	}

	public ApiMatch getMatch() {
		return match;
	}

	public void setMatch(ApiMatch match) {
		this.match = match;
	}

	public ApiLeague.ApiFixtureLeague getLeague() {
		return league;
	}

	public void setLeague(ApiLeague.ApiFixtureLeague league) {
		this.league = league;
	}

	public ApiTeam getTeams() {
		return teams;
	}

	public void setTeams(ApiTeam teams) {
		this.teams = teams;
	}

	public ApiGoal getGoals() {
		return goals;
	}

	public void setGoals(ApiGoal goals) {
		this.goals = goals;
	}

	public ApiScore getScore() {
		return score;
	}

	public void setScore(ApiScore score) {
		this.score = score;
	}

	public List<ApiEvent> getEvents() {
		return events;
	}

	public void setEvents(List<ApiEvent> events) {
		this.events = events;
	}
}
