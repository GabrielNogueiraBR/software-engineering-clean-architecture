package com.project.cleanarchitecture.application.factory;

import org.springframework.stereotype.Component;

import com.project.cleanarchitecture.application.dto.UserCoursesDto;
import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.UserWithBalanceDto;
import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.vo.CPF;
import com.project.cleanarchitecture.domain.vo.Email;

@Component
public class UserMapper {

    public User toModel(UserCreateDto userDto) {
        String name = userDto.getName();
        Email email = new Email(userDto.getEmail());
        CPF cpf = new CPF(userDto.getCPF());
        String password = userDto.getPassword();
    	
    	User user = new User(name,email,cpf,password);
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail().getValue());
        userDto.setCPF(user.getCPF().getValue());
        userDto.setId(user.getId());
        return userDto;
    }
    
    public UserWithBalanceDto toUserWithBalanceDto(User user) {
    	UserWithBalanceDto userWithBalanceDto = new UserWithBalanceDto();
    	userWithBalanceDto.setId(user.getId());
    	userWithBalanceDto.setName(user.getName());
    	userWithBalanceDto.setBalance(user.getBalance());
    	return userWithBalanceDto;
    }
    
    public UserCoursesDto toUserCoursesDto(User user) {
    	UserCoursesDto userCoursesDto = new UserCoursesDto();
    	userCoursesDto.setName(user.getName());
    	userCoursesDto.setEmail(user.getEmail().getValue());
    	return userCoursesDto;
    }

}
