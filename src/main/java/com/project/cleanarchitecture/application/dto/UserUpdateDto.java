package com.project.cleanarchitecture.application.dto;

public class UserUpdateDto {

	private String name;

	public UserUpdateDto() {
	}

	public UserUpdateDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
