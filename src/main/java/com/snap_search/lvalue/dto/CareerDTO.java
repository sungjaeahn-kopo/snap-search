package com.snap_search.lvalue.dto;

public class CareerDTO {

	private Integer teamId;
	private String teamName;
	private String teamLogo;
	private String startDate;
	private String endDate;

	public CareerDTO(Integer teamId, String teamName, String teamLogo, String startDate, String endDate) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamLogo = teamLogo;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
