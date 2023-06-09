package com.project.cleanarchitecture.application.validator;

import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.UserUpdateDto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }
    
    public void validateUserDto(UserCreateDto userDto) throws Exception {
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            throw new Exception("Full name cannot be empty");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new Exception("Email cannot be empty");
        }
    }
    
    public void validateUserUpdateDto(UserUpdateDto userDto) throws Exception {
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            throw new Exception("Full name cannot be empty");
        }
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        UserDto dto = (UserDto) target;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        
        if (!dto.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.rejectValue("email", "Pattern.userDto.email");
        }
    }
    
}
