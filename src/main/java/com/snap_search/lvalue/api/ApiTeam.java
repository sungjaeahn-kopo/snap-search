package com.snap_search.lvalue.api;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 */
public class ApiTeam {
	private Long id;
	private String name;
	private String logo;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}
