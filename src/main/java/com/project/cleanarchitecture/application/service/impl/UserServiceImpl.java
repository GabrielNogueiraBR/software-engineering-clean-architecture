package com.project.cleanarchitecture.application.service.impl;

import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.service.interfaces.UserService;
import com.project.cleanarchitecture.application.validator.UserValidator;
import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserValidator userValidator;

    @Override
    public UserDto createUser(UserDto userDto){
        userValidator.validateUserDto(userDto);
        User user = UserMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userValidator.validateUserDto(userDto);
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
