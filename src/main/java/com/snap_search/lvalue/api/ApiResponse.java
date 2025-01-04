package com.snap_search.lvalue.api;

import java.util.List;

public class ApiResponse {
	private List<ApiCountry> response;

	public List<ApiCountry> getResponse() {
		return response;
	}

	public void setResponse(List<ApiCountry> response) {
		this.response = response;
	}
}
