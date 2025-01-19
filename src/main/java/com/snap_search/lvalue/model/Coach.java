package com.snap_search.lvalue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coaches")
public class Coach {

	@Id
	private Long id; // api 제공 코치 ID
	private String name; // 코치 이름
	private String age; // 코치 종류
	private String nationality; // 국적
	private String photo; // 코치 이미지

	// @Column(name = "team_id")
	// private Long teamId; // 현재 소속 팀 id
	// @Column(name = "career_0_start")
	// private String carrerStart; // 최근 소속 팀 시작
	// @Column(name = "career_0_end")
	// private String carrerEnd; // 최근 소속 팀 종료

	// 코치 커리어
	// @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	// private List<Career> careers; // 커리어 리스트

	public Coach() {

	}

	public Coach(Long id, String name, String age, String nationality, String photo) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.nationality = nationality;
		this.photo = photo;
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

}
