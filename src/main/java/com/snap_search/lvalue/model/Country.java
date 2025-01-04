package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {

	@Id
	private String code; // 국가 코드
	private String name; // 국가명
	private String flag; // 국기 url

	public Country(String code, String name, String flag) {
		this.code = code;
		this.name = name;
		this.flag = flag;
	}

	public Country() {

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
