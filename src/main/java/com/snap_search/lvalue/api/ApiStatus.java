package com.snap_search.lvalue.api;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiStatus {
	private String fixtureStatusLong;
	private String fixtureStatusShort;
	private Long fixtureStatusElapsed;
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
