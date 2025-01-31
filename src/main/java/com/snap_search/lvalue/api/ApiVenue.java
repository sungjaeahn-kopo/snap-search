package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiVenue {
	@JsonProperty("id")
	private Long fixtureVenueId;
	@JsonProperty("name")
	private String fixtureVenueName;
	@JsonProperty("city")
	private String fixtureVenueCity;

	public Long getFixtureVenueId() {
		return fixtureVenueId;
	}

	public void setFixtureVenueId(Long fixtureVenueId) {
		this.fixtureVenueId = fixtureVenueId;
	}

	public String getFixtureVenueName() {
		return fixtureVenueName;
	}

	public void setFixtureVenueName(String fixtureVenueName) {
		this.fixtureVenueName = fixtureVenueName;
	}

	public String getFixtureVenueCity() {
		return fixtureVenueCity;
	}

	public void setFixtureVenueCity(String fixtureVenueCity) {
		this.fixtureVenueCity = fixtureVenueCity;
	}
}
