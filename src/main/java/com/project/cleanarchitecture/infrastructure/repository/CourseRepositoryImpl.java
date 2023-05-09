package com.project.cleanarchitecture.infrastructure.repository;

import com.project.cleanarchitecture.domain.model.Course;
import com.project.cleanarchitecture.domain.repository.CourseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryImpl extends CourseRepository, JpaRepository<Course, Long> {
}

