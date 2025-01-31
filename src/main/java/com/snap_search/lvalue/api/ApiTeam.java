package com.snap_search.lvalue.api;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 */
public class ApiTeam {
	private Long id;
	private String name;
	private String logo;
	private ApiFixtureTeam fixtureTeam;

	public static class ApiFixtureTeam {
		private Long teamsHomeId;
		private String teamsHomeName;
		private String teamsHomeLogo;
		private String teamsHomeWinner;
		private Long teamsAwayId;
		private String teamsAwayName;
		private String teamsAwayLogo;
		private String teamsAwayWinner;

		public Long getTeamsHomeId() {
			return teamsHomeId;
		}

		public void setTeamsHomeId(Long teamsHomeId) {
			this.teamsHomeId = teamsHomeId;
		}

		public String getTeamsHomeName() {
			return teamsHomeName;
		}

		public void setTeamsHomeName(String teamsHomeName) {
			this.teamsHomeName = teamsHomeName;
		}

		public String getTeamsHomeLogo() {
			return teamsHomeLogo;
		}

		public void setTeamsHomeLogo(String teamsHomeLogo) {
			this.teamsHomeLogo = teamsHomeLogo;
		}

		public String getTeamsHomeWinner() {
			return teamsHomeWinner;
		}

		public void setTeamsHomeWinner(String teamsHomeWinner) {
			this.teamsHomeWinner = teamsHomeWinner;
		}

		public Long getTeamsAwayId() {
			return teamsAwayId;
		}

		public void setTeamsAwayId(Long teamsAwayId) {
			this.teamsAwayId = teamsAwayId;
		}

		public String getTeamsAwayName() {
			return teamsAwayName;
		}

		public void setTeamsAwayName(String teamsAwayName) {
			this.teamsAwayName = teamsAwayName;
		}

		public String getTeamsAwayLogo() {
			return teamsAwayLogo;
		}

		public void setTeamsAwayLogo(String teamsAwayLogo) {
			this.teamsAwayLogo = teamsAwayLogo;
		}

		public String getTeamsAwayWinner() {
			return teamsAwayWinner;
		}

		public void setTeamsAwayWinner(String teamsAwayWinner) {
			this.teamsAwayWinner = teamsAwayWinner;
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

	public ApiFixtureTeam getFixtureTeam() {
		return fixtureTeam;
	}

	public void setFixtureTeam(ApiFixtureTeam fixtureTeam) {
		this.fixtureTeam = fixtureTeam;
	}
}
