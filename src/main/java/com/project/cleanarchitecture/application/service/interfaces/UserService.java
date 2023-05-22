package com.project.cleanarchitecture.application.service.interfaces;

import com.project.cleanarchitecture.application.dto.UserCoursesDto;
import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.UserUpdateDto;
import com.project.cleanarchitecture.application.dto.UserWithBalanceDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

	public UserDto createUser(UserCreateDto dto) throws Exception;

	public UserDto updateUser(Long id, UserUpdateDto userDto) throws Exception;

    void deleteUser(Long id);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
    
    UserWithBalanceDto updateBalance(Long id, BigDecimal balance);

	UserCoursesDto getCoursesByUser(Long id);
}
