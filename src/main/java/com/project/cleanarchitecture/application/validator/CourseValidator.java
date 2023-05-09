package com.project.cleanarchitecture.application.validator;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
public class CourseValidator {

    public void validateCourseDto(CourseDto courseDto) throws ValidationException {
        if (ObjectUtils.isEmpty(courseDto.getName())) {
            throw new ValidationException("Course name cannot be empty");
        }

        if (ObjectUtils.isEmpty(courseDto.getDescription())) {
            throw new ValidationException("Course description cannot be empty");
        }

        if (courseDto.getPrice() == null || courseDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Course price must be greater than zero");
        }
    }
}
