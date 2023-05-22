package com.project.cleanarchitecture.infrastructure.repository;

import com.project.cleanarchitecture.domain.model.Course;
import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.repository.CourseRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryImpl extends CourseRepository {
	@Query("SELECT c FROM Course c WHERE c.role = :role")
	List<Course> findByRole(Role role);
}

