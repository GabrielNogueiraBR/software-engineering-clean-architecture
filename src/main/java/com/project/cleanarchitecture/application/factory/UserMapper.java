package com.project.cleanarchitecture.application.factory;

import org.springframework.stereotype.Component;

import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.domain.model.User;

@Component
public class UserMapper {

    public static User toModel(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
