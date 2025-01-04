package com.snap_search.lvalue.api;

import java.util.List;

/**
 * 기존에는 Country 테이블 하나여서 List의 타입을 Country로 했으나,
 * 확장성을 위해 Generic으로 변경
 */
public class ApiResponse<T> {
	private List<T> response;

	public List<T> getResponse() {
		return response;
	}

	public void setResponse(List<T> response) {
		this.response = response;
	}
}
