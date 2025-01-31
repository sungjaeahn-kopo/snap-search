package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiMatch {
	@JsonProperty("id")
	private Long fixtureId; // Primary Key
	@JsonProperty("date")
	private String fixtureDate; // Player's back number
	@JsonProperty("timezone")
	private String fixtureTimezone; // Player Age
	@JsonProperty("timestamp")
	private Long fixtureTimeStamp; // Player position
	@JsonProperty("referee")
	private String fixtureReferee; // Player Name

	@JsonProperty("periods")
	private ApiPeriods periods; // 전후반 시작 시간
	@JsonProperty("venue")
	private ApiVenue venue; // 경기장 정보
	@JsonProperty("status")
	private ApiStatus status; // 경기 상태 정보

	public Long getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(Long fixtureId) {
		this.fixtureId = fixtureId;
	}

	public String getFixtureDate() {
		return fixtureDate;
	}

	public void setFixtureDate(String fixtureDate) {
		this.fixtureDate = fixtureDate;
	}

	public String getFixtureTimezone() {
		return fixtureTimezone;
	}

	public void setFixtureTimezone(String fixtureTimezone) {
		this.fixtureTimezone = fixtureTimezone;
	}

	public Long getFixtureTimeStamp() {
		return fixtureTimeStamp;
	}

	public void setFixtureTimeStamp(Long fixtureTimeStamp) {
		this.fixtureTimeStamp = fixtureTimeStamp;
	}

	public String getFixtureReferee() {
		return fixtureReferee;
	}

	public void setFixtureReferee(String fixtureRefree) {
		this.fixtureReferee = fixtureRefree;
	}

	public ApiPeriods getPeriods() {
		return periods;
	}

	public void setPeriods(ApiPeriods periods) {
		this.periods = periods;
	}

	public ApiVenue getVenue() {
		return venue;
	}

	public void setVenue(ApiVenue venue) {
		this.venue = venue;
	}

	public ApiStatus getStatus() {
		return status;
	}

	public void setStatus(ApiStatus status) {
		this.status = status;
	}
}
