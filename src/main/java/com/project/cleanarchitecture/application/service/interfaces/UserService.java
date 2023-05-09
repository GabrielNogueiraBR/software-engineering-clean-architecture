package com.project.cleanarchitecture.application.service.interfaces;

import com.project.cleanarchitecture.application.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto) throws Exception;

    UserDto updateUser(Long id, UserDto userDto) throws Exception;

    void deleteUser(Long id);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
}
