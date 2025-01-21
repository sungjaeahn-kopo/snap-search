package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Player {

	@Id
	private Long id; // Primary Key
	private String name; // Player Name
	private Long age; // Player Age
	private Long number; // Player's back number
	private String position; // Player position
	private String photo; // Player Image
	@Transient
	private Long teamId; // Player's team id

	@ManyToOne
	@JoinColumn(name = "team_id", referencedColumnName = "teamId") // 외래 키 설정
	private CountryLeagueMap countryLeagueMap; // CountryLeagueMap와의 관계

	public Player() {

	}

	public Player(Long id, String name, Long age, Long number, String position, String photo, Long teamId) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.number = number;
		this.position = position;
		this.photo = photo;
		this.teamId = teamId;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public CountryLeagueMap getCountryLeagueMap() {
		return countryLeagueMap;
	}

	public void setCountryLeagueMap(CountryLeagueMap countryLeagueMap) {
		this.countryLeagueMap = countryLeagueMap;
	}
}
