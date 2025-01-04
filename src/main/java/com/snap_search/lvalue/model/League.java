package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class League {

	@Id
	private Long id; // api 제공 리그 ID
	private String name; // 리그 이름
	private String type; // 리그 종류
	private String logo; // 리그 로고

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public League(Long id, String name, String type, String logo, Country country) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.logo = logo;
		this.country = country;
	}

	public League() {

	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}
