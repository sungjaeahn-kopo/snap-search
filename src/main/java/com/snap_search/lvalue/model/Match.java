package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fixture_stats")
public class Match {
	@Id
	private Long fixtureId;
	private String fixtureReferee;
	private String fixtureDate;
	private String fixtureStatusLong;
	private String fixtureStatusShort;
	private int fixtureStatusElapsed;
	private int fixtureStatusExtra;
	private String leagueName;
	private int leagueId;
	private int leagueSeason;
	private String leagueRound;

	private int teamsHomeId;
	private String teamsHomeName;
	private String teamsHomeLogo;
	private String teamsHomeWinner;

	private int teamsAwayId;
	private String teamsAwayName;
	private String teamsAwayLogo;
	private String teamsAwayWinner;

	private Integer goalsHome;
	private Integer goalsAway;
	private Integer scoreHalftimeHome;
	private Integer scoreHalftimeAway;
	private Integer scoreFulltimeHome;
	private Integer scoreFulltimeAway;

	private int fixtureVenueId;
	private String fixtureVenueName;
	private String fixtureVenueCity;

	private int fixturePeriodsFirst;
	private int fixturePeriodsSecond;

	public Long getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(Long fixtureId) {
		this.fixtureId = fixtureId;
	}

	public String getFixtureReferee() {
		return fixtureReferee;
	}

	public void setFixtureReferee(String fixtureReferee) {
		this.fixtureReferee = fixtureReferee;
	}

	public String getFixtureDate() {
		return fixtureDate;
	}

	public void setFixtureDate(String fixtureDate) {
		this.fixtureDate = fixtureDate;
	}

	public String getFixtureStatusLong() {
		return fixtureStatusLong;
	}

	public void setFixtureStatusLong(String fixtureStatusLong) {
		this.fixtureStatusLong = fixtureStatusLong;
	}

	public String getFixtureStatusShort() {
		return fixtureStatusShort;
	}

	public void setFixtureStatusShort(String fixtureStatusShort) {
		this.fixtureStatusShort = fixtureStatusShort;
	}

	public int getFixtureStatusElapsed() {
		return fixtureStatusElapsed;
	}

	public void setFixtureStatusElapsed(int fixtureStatusElapsed) {
		this.fixtureStatusElapsed = fixtureStatusElapsed;
	}

	public int getFixtureStatusExtra() {
		return fixtureStatusExtra;
	}

	public void setFixtureStatusExtra(int fixtureStatusExtra) {
		this.fixtureStatusExtra = fixtureStatusExtra;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public int getLeagueSeason() {
		return leagueSeason;
	}

	public void setLeagueSeason(int leagueSeason) {
		this.leagueSeason = leagueSeason;
	}

	public String getLeagueRound() {
		return leagueRound;
	}

	public void setLeagueRound(String leagueRound) {
		this.leagueRound = leagueRound;
	}

	public int getTeamsHomeId() {
		return teamsHomeId;
	}

	public void setTeamsHomeId(int teamsHomeId) {
		this.teamsHomeId = teamsHomeId;
	}

	public String getTeamsHomeName() {
		return teamsHomeName;
	}

	public void setTeamsHomeName(String teamsHomeName) {
		this.teamsHomeName = teamsHomeName;
	}

	public String getTeamsHomeLogo() {
		return teamsHomeLogo;
	}

	public void setTeamsHomeLogo(String teamsHomeLogo) {
		this.teamsHomeLogo = teamsHomeLogo;
	}

	public String getTeamsHomeWinner() {
		return teamsHomeWinner;
	}

	public void setTeamsHomeWinner(String teamsHomeWinner) {
		this.teamsHomeWinner = teamsHomeWinner;
	}

	public int getTeamsAwayId() {
		return teamsAwayId;
	}

	public void setTeamsAwayId(int teamsAwayId) {
		this.teamsAwayId = teamsAwayId;
	}

	public String getTeamsAwayName() {
		return teamsAwayName;
	}

	public void setTeamsAwayName(String teamsAwayName) {
		this.teamsAwayName = teamsAwayName;
	}

	public String getTeamsAwayLogo() {
		return teamsAwayLogo;
	}

	public void setTeamsAwayLogo(String teamsAwayLogo) {
		this.teamsAwayLogo = teamsAwayLogo;
	}

	public String getTeamsAwayWinner() {
		return teamsAwayWinner;
	}

	public void setTeamsAwayWinner(String teamsAwayWinner) {
		this.teamsAwayWinner = teamsAwayWinner;
	}

	public Integer getGoalsHome() {
		return goalsHome;
	}

	public void setGoalsHome(Integer goalsHome) {
		this.goalsHome = goalsHome;
	}

	public Integer getGoalsAway() {
		return goalsAway;
	}

	public void setGoalsAway(Integer goalsAway) {
		this.goalsAway = goalsAway;
	}

	public Integer getScoreHalftimeHome() {
		return scoreHalftimeHome;
	}

	public void setScoreHalftimeHome(Integer scoreHalftimeHome) {
		this.scoreHalftimeHome = scoreHalftimeHome;
	}

	public Integer getScoreHalftimeAway() {
		return scoreHalftimeAway;
	}

	public void setScoreHalftimeAway(Integer scoreHalftimeAway) {
		this.scoreHalftimeAway = scoreHalftimeAway;
	}

	public Integer getScoreFulltimeHome() {
		return scoreFulltimeHome;
	}

	public void setScoreFulltimeHome(Integer scoreFulltimeHome) {
		this.scoreFulltimeHome = scoreFulltimeHome;
	}

	public Integer getScoreFulltimeAway() {
		return scoreFulltimeAway;
	}

	public void setScoreFulltimeAway(Integer scoreFulltimeAway) {
		this.scoreFulltimeAway = scoreFulltimeAway;
	}

	public int getFixtureVenueId() {
		return fixtureVenueId;
	}

	public void setFixtureVenueId(int fixtureVenueId) {
		this.fixtureVenueId = fixtureVenueId;
	}

	public String getFixtureVenueName() {
		return fixtureVenueName;
	}

	public void setFixtureVenueName(String fixtureVenueName) {
		this.fixtureVenueName = fixtureVenueName;
	}

	public String getFixtureVenueCity() {
		return fixtureVenueCity;
	}

	public void setFixtureVenueCity(String fixtureVenueCity) {
		this.fixtureVenueCity = fixtureVenueCity;
	}

	public int getFixturePeriodsFirst() {
		return fixturePeriodsFirst;
	}

	public void setFixturePeriodsFirst(int fixturePeriodsFirst) {
		this.fixturePeriodsFirst = fixturePeriodsFirst;
	}

	public int getFixturePeriodsSecond() {
		return fixturePeriodsSecond;
	}

	public void setFixturePeriodsSecond(int fixturePeriodsSecond) {
		this.fixturePeriodsSecond = fixturePeriodsSecond;
	}
}
