package com.project.cleanarchitecture.domain.repository;

import com.project.cleanarchitecture.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}