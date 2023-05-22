package com.project.cleanarchitecture.domain.repository;

import com.project.cleanarchitecture.domain.model.Course;
import com.project.cleanarchitecture.domain.model.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByRole(Role role);
}