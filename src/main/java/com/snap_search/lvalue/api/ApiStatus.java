package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiStatus {
	@JsonProperty("long")
	private String fixtureStatusLong;
	@JsonProperty("short")
	private String fixtureStatusShort;
	@JsonProperty("elapsed")
	private Long fixtureStatusElapsed;
	@JsonProperty("extra")
	private Long fixtureStatusExtra;

	public String getFixtureStatusLong() {
		return fixtureStatusLong;
	}

	public void setFixtureStatusLong(String fixtureStatusLong) {
		this.fixtureStatusLong = fixtureStatusLong;
	}

	public String getFixtureStatusShort() {
		return fixtureStatusShort;
	}

	public void setFixtureStatusShort(String fixtureStatusShort) {
		this.fixtureStatusShort = fixtureStatusShort;
	}

	public Long getFixtureStatusElapsed() {
		return fixtureStatusElapsed;
	}

	public void setFixtureStatusElapsed(Long fixtureStatusElapsed) {
		this.fixtureStatusElapsed = fixtureStatusElapsed;
	}

	public Long getFixtureStatusExtra() {
		return fixtureStatusExtra;
	}

	public void setFixtureStatusExtra(Long fixtureStatusExtra) {
		this.fixtureStatusExtra = fixtureStatusExtra;
	}
}
