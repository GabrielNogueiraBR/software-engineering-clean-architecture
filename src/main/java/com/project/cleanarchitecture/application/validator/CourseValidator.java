package com.project.cleanarchitecture.application.validator;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
public class CourseValidator {

    public void validateCourseDto(CourseDto courseDto) throws ValidationException {
        if (courseDto.getPrice() == null || courseDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Course price must be greater than zero");
        }
    }
}
