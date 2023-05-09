package com.project.cleanarchitecture.application.factory;

import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    User toModel(UserDto dto);
    
    UserDto toDto(User model);
    
}
