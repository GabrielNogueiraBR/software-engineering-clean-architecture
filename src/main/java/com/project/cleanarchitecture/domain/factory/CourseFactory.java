package com.project.cleanarchitecture.domain.factory;

import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.Course;

public class CourseFactory {
	private Course course;
	
	public CourseFactory withTitleAndRole(String title, Role role) {
		this.course = new Course(title, role);
		return this;
	}
	
	public Course create() {
		return this.course;
	}
}
