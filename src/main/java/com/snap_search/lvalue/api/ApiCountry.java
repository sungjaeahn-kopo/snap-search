package com.snap_search.lvalue.api;

/**
 * API 호출시 응답 데이터를 매핑하기 위한 클래스
 */
public class ApiCountry {
	private Long id;
	private String code;
	private String name;
	private String flag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
