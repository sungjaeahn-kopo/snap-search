package com.snap_search.lvalue.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Team {
	@Id
	private Long id;
	private String name;
	private String logo;
	private String leagueIds; // 쉼표로 구분된 리그 ID 문자열

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Player> players;

	/**
	 * 리그 ID를 리스트 형태로 변환
	 * team 테이블의 league id와 타 테이블의 league_id가 정확히 매핑되지 않음
	 * league_id(team): List<String>, league_id(countryLeagueMap): number
	 * @return
	 */
	@Transient
	public List<Long> getLeagueIdList() {
		if (leagueIds == null || leagueIds.isEmpty()) {
			return List.of();
		}
		return Arrays.stream(leagueIds.split(","))
			.map(String::trim)
			.map(Long::valueOf)
			.collect(Collectors.toList());
	}

	@Transient
	public void setLeagueIdList(List<Long> leagueIdList) {
		this.leagueIds = leagueIdList.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(","));
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLeagueIds() {
		return leagueIds;
	}

	public void setLeagueIds(String leagueIds) {
		this.leagueIds = leagueIds;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
