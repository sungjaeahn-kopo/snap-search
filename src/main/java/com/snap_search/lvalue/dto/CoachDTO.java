package com.snap_search.lvalue.dto;

import java.util.List;

public class CoachDTO {

	private Long id;
	private String name;
	private String age;
	private String nationality;
	private String photo;
	private List<CareerDTO> careers; // 커리어 리스트 포함

	public CoachDTO() {
	}

	public CoachDTO(Long id, String name, String age, String nationality, String photo,
		List<CareerDTO> careers) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.nationality = nationality;
		this.photo = photo;
		this.careers = careers;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<CareerDTO> getCareers() {
		return careers;
	}

	public void setCareers(List<CareerDTO> careers) {
		this.careers = careers;
	}
}
