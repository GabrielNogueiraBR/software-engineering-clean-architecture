package com.project.cleanarchitecture.application.dto;

import java.util.List;

public class UserCoursesDto {
	private String name;
    
    private String email;
    
    private List<CourseDto> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}
}
