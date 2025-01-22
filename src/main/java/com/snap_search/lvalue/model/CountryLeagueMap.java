package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "league_team_map")
public class CountryLeagueMap {

	@Id
	private Long id;
	private String leagueSeason;
	private Long countryId;
	private String countryName;
	private String countryFlag;

	private Long leagueId;
	private String leagueName;
	private String leagueLogo;

	private Long teamId;
	private String teamName;
	private String teamLogo;

	public CountryLeagueMap() {
	}

	public CountryLeagueMap(Long id, String leagueSeason, Long countryId, Long leagueId, String countryName,
		String countryFlag) {
		this.id = id;
		this.leagueSeason = leagueSeason;
		this.countryId = countryId;
		this.leagueId = leagueId;
		this.countryName = countryName;
		this.countryFlag = countryFlag;
	}

	/**
	 * 시즌 선택후, 나라 리스트 출력시 사용되는 생성자.
	 */
	public CountryLeagueMap(String leagueSeason, Long countryId, String countryName, String countryFlag) {
		this.leagueSeason = leagueSeason;
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryFlag = countryFlag;
	}

	/**
	 * 시즌, 국가 선택후, 리그 리스트 출력시 사용되는 생성자.
	 */
	public CountryLeagueMap(String leagueSeason, Long countryId, String countryName, String countryFlag, Long leagueId,
		String leagueName, String leagueLogo) {
		this.leagueSeason = leagueSeason;
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryFlag = countryFlag;
		this.leagueId = leagueId;
		this.leagueName = leagueName;
		this.leagueLogo = leagueLogo;
	}

	/**
	 * 시즌, 국가, 리그 선택후, 클럽 리스트 출력시 사용되는 생성자.
	 */
	public CountryLeagueMap(String leagueSeason, String countryName, String countryFlag, Long teamId, String teamName,
		String teamLogo) {
		this.leagueSeason = leagueSeason;
		this.countryName = countryName;
		this.countryFlag = countryFlag;
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamLogo = teamLogo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeagueSeason() {
		return leagueSeason;
	}

	public void setLeagueSeason(String leagueSeason) {
		this.leagueSeason = leagueSeason;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryFlag() {
		return countryFlag;
	}

	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getLeagueLogo() {
		return leagueLogo;
	}

	public void setLeagueLogo(String leagueLogo) {
		this.leagueLogo = leagueLogo;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamLogo() {
		return teamLogo;
	}

	public void setTeamLogo(String teamLogo) {
		this.teamLogo = teamLogo;
	}

	// public List<Player> getPlayers() {
	// 	return players;
	// }
	//
	// public void setPlayers(List<Player> players) {
	// 	this.players = players;
	// }
}
