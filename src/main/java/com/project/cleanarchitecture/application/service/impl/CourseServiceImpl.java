package com.project.cleanarchitecture.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.application.factory.CourseMapper;
import com.project.cleanarchitecture.application.service.interfaces.CourseService;
import com.project.cleanarchitecture.application.validator.CourseValidator;
import com.project.cleanarchitecture.domain.exception.ValidationException;
import com.project.cleanarchitecture.domain.model.Course;
import com.project.cleanarchitecture.domain.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
	private CourseValidator courseValidator;
    
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseDto createCourse(CourseDto courseDto) throws ValidationException {
        courseValidator.validateCourseDto(courseDto);
        Course course = courseMapper.toModel(courseDto);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDto(savedCourse);
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) throws ValidationException {
        courseValidator.validateCourseDto(courseDto);
        Course course = courseMapper.toModel(courseDto);
        course.setId(id);
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toDto(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            return courseMapper.toDto(course);
        }
        return null;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDtoList(courses);
    }
}
