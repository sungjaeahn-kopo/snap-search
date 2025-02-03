package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 */
public class ApiTeam {
	private Long id;
	private String name;
	private String logo;
	@JsonProperty("home")
	private ApiFixtureTeamHome home;
	@JsonProperty("away")
	private ApiFixtureTeamAway away;

	public static class ApiFixtureTeamHome {
		private Long id;
		private String name;
		private String logo;
		private String winner;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public String getWinner() {
			return winner;
		}

		public void setWinner(String winner) {
			this.winner = winner;
		}
	}

	public static class ApiFixtureTeamAway {
		private Long id;
		private String name;
		private String logo;
		private String winner;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public String getWinner() {
			return winner;
		}

		public void setWinner(String winner) {
			this.winner = winner;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public ApiFixtureTeamHome getHome() {
		return home;
	}

	public void setHome(ApiFixtureTeamHome home) {
		this.home = home;
	}

	public ApiFixtureTeamAway getAway() {
		return away;
	}

	public void setAway(ApiFixtureTeamAway away) {
		this.away = away;
	}
}
