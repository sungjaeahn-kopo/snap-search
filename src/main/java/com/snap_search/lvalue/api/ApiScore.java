package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiScore {
	@JsonProperty("halftime")
	private ApiScoreHalf halfTime;
	@JsonProperty("fulltime")
	private ApiScoreFull fullTime;
	@JsonProperty("extratime")
	private ApiScoreExtra extraTime;
	@JsonProperty("penalty")
	private ApiScorePenalty penalty;

	public static class ApiScoreHalf {
		private Long home;
		private Long away;

		public Long getHome() {
			return home;
		}

		public void setHome(Long home) {
			this.home = home;
		}

		public Long getAway() {
			return away;
		}

		public void setAway(Long away) {
			this.away = away;
		}
	}

	public static class ApiScoreFull {
		private Long home;
		private Long away;

		public Long getHome() {
			return home;
		}

		public void setHome(Long home) {
			this.home = home;
		}

		public Long getAway() {
			return away;
		}

		public void setAway(Long away) {
			this.away = away;
		}
	}

	public static class ApiScoreExtra {
		private Long home;
		private Long away;

		public Long getHome() {
			return home;
		}

		public void setHome(Long home) {
			this.home = home;
		}

		public Long getAway() {
			return away;
		}

		public void setAway(Long away) {
			this.away = away;
		}
	}

	public static class ApiScorePenalty {
		private Long home;
		private Long away;

		public Long getHome() {
			return home;
		}

		public void setHome(Long home) {
			this.home = home;
		}

		public Long getAway() {
			return away;
		}

		public void setAway(Long away) {
			this.away = away;
		}
	}

	public ApiScoreHalf getHalfTime() {
		return halfTime;
	}

	public void setHalfTime(ApiScoreHalf halfTime) {
		this.halfTime = halfTime;
	}

	public ApiScoreFull getFullTime() {
		return fullTime;
	}

	public void setFullTime(ApiScoreFull fullTime) {
		this.fullTime = fullTime;
	}

	public ApiScoreExtra getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(ApiScoreExtra extraTime) {
		this.extraTime = extraTime;
	}

	public ApiScorePenalty getPenalty() {
		return penalty;
	}

	public void setPenalty(ApiScorePenalty penalty) {
		this.penalty = penalty;
	}
}
