package com.project.cleanarchitecture.application.validator;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;


@Component
public class CourseValidator {
    
    public void validateCourseDto(CourseDto courseDto) throws ValidationException {
        if (courseDto.getRole() == null) {
            throw new ValidationException("Role cannot be empty or null");
        }
    }
}
