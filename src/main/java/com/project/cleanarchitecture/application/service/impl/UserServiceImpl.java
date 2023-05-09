package com.project.cleanarchitecture.application.service.impl;

import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.factory.UserMapper;
import com.project.cleanarchitecture.application.service.interfaces.UserService;
import com.project.cleanarchitecture.application.validator.UserValidator;
import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws Exception{
        try {
        	userValidator.validateUserDto(userDto);
            User user = userMapper.toModel(userDto);
            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        }catch(Exception e){
        	throw new Exception("Error on create User with DTO");
        }
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws Exception{
    	try {
    		Optional<User> userOpt = userRepository.findById(id);
    		User user = userOpt.get();
            userValidator.validateUserDto(userDto);
            
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            User updatedUser = userRepository.save(user);
            return userMapper.toDto(updatedUser);
    	}catch(Exception e) {
    		throw new Exception("User not found with id: " + id);
    	}
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
    	Optional<User> userOpt = userRepository.findById(id);
		User user = userOpt.get();
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
