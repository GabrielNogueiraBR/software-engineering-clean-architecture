package com.project.cleanarchitecture.application.service.interfaces;

import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

	public UserDto createUser(UserCreateDto dto) throws Exception;

	public UserDto updateUser(Long id, UserUpdateDto userDto) throws Exception;

    void deleteUser(Long id);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
}
