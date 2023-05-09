package com.project.cleanarchitecture.application.factory;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.domain.model.Course;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "id", ignore = true)
    Course toModel(CourseDto courseDto);

    CourseDto toDto(Course course);
    
    public default List<CourseDto> toDtoList(List<Course> courses) {
        return courses.stream().map(this::toDto).collect(Collectors.toList());
    }

}
