package com.snap_search.lvalue.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 * Fixture
 */
public class ApiPeriods {
	@JsonProperty("first")
	private Long fixturePeriodsFirst;
	@JsonProperty("second")
	private Long fixturePeriodsSecond;

	public Long getFixturePeriodsFirst() {
		return fixturePeriodsFirst;
	}

	public void setFixturePeriodsFirst(Long fixturePeriodsFirst) {
		this.fixturePeriodsFirst = fixturePeriodsFirst;
	}

	public Long getFixturePeriodsSecond() {
		return fixturePeriodsSecond;
	}

	public void setFixturePeriodsSecond(Long fixturePeriodsSecond) {
		this.fixturePeriodsSecond = fixturePeriodsSecond;
	}
}
