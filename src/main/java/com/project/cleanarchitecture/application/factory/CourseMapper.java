package com.project.cleanarchitecture.application.factory;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.domain.model.Course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toModel(CourseDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setTitle(courseDto.getTitle());
        return course;
    }

    public CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        return courseDto;
    }

    public List<CourseDto> toDtoList(List<Course> courses) {
        return courses.stream().map(this::toDto).collect(Collectors.toList());
    }

}
