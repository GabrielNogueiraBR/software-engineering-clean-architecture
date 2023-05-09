package com.project.cleanarchitecture.application.service.interfaces;

import java.util.List;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto) throws ValidationException;
    CourseDto updateCourse(Long id, CourseDto courseDto) throws ValidationException;
    void deleteCourse(Long id);
    CourseDto getCourseById(Long id);
    List<CourseDto> getAllCourses();
}
