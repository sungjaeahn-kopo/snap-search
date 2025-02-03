package com.snap_search.lvalue.dto;

public class PlayerDTO {

	private Long id; // Primary Key
	private String name; // Player Name
	private Long age; // Player Age
	private Long number; // Player's back number
	private String position; // Player position
	private String photo; // Player Image

	public PlayerDTO() {
	}

	public PlayerDTO(Long id, String name, Long age, Long number, String position, String photo) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.number = number;
		this.position = position;
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
}
