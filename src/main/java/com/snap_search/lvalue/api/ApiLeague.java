package com.snap_search.lvalue.api;

import java.util.List;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 */
public class ApiLeague {
	private LeagueData league; // 리그 데이터
	private ApiCountry country; // 국가 데이터
	private List<SeasonData> seasons; // 시즌 데이터
	private ApiFixtureLeague fixtureLeague;

	// 내부 클래스: League 데이터
	public static class LeagueData {
		private Long id; // 리그 ID
		private String name; // 리그 이름
		private String type; // 리그 타입
		private String logo; // 리그 로고 URL

		// Getters and Setters
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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}
	}

	// 내부 클래스: Season 데이터
	public static class SeasonData {
		private Integer year; // 시즌 연도
		private String start; // 시즌 시작 날짜
		private String end; // 시즌 종료 날짜

		// Getters and Setters
		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public String getEnd() {
			return end;
		}

		public void setEnd(String end) {
			this.end = end;
		}
	}

	/**
	 * API fixtures의 league 객체를 받기 위함
	 */
	public static class ApiFixtureLeague {
		private Long leagueId;
		private String leagueName;
		private String leagueCountry;
		private String leagueLogo;
		private String leagueFlag;
		private Long leagueSeason;
		private String leagueRound;

		public Long getLeagueId() {
			return leagueId;
		}

		public void setLeagueId(Long leagueId) {
			this.leagueId = leagueId;
		}

		public String getLeagueName() {
			return leagueName;
		}

		public void setLeagueName(String leagueName) {
			this.leagueName = leagueName;
		}

		public String getLeagueCountry() {
			return leagueCountry;
		}

		public void setLeagueCountry(String leagueCountry) {
			this.leagueCountry = leagueCountry;
		}

		public String getLeagueLogo() {
			return leagueLogo;
		}

		public void setLeagueLogo(String leagueLogo) {
			this.leagueLogo = leagueLogo;
		}

		public String getLeagueFlag() {
			return leagueFlag;
		}

		public void setLeagueFlag(String leagueFlag) {
			this.leagueFlag = leagueFlag;
		}

		public Long getLeagueSeason() {
			return leagueSeason;
		}

		public void setLeagueSeason(Long leagueSeason) {
			this.leagueSeason = leagueSeason;
		}

		public String getLeagueRound() {
			return leagueRound;
		}

		public void setLeagueRound(String leagueRound) {
			this.leagueRound = leagueRound;
		}
	}

	// Getters and Setters
	public LeagueData getLeague() {
		return league;
	}

	public void setLeague(LeagueData league) {
		this.league = league;
	}

	public ApiCountry getCountry() {
		return country;
	}

	public void setCountry(ApiCountry country) {
		this.country = country;
	}

	public List<SeasonData> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<SeasonData> seasons) {
		this.seasons = seasons;
	}

	public ApiFixtureLeague getFixtureLeague() {
		return fixtureLeague;
	}

	public void setFixtureLeague(ApiFixtureLeague fixtureLeague) {
		this.fixtureLeague = fixtureLeague;
	}
}
