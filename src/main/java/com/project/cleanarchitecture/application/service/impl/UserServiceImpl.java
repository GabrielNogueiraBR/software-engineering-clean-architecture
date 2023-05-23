package com.project.cleanarchitecture.application.service.impl;

import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.application.dto.UserCoursesDto;
import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.UserUpdateDto;
import com.project.cleanarchitecture.application.dto.UserWithBalanceDto;
import com.project.cleanarchitecture.application.factory.CourseMapper;
import com.project.cleanarchitecture.application.factory.UserMapper;
import com.project.cleanarchitecture.application.service.interfaces.UserService;
import com.project.cleanarchitecture.application.validator.UserValidator;
import com.project.cleanarchitecture.domain.model.Course;
import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.repository.CourseRepository;
import com.project.cleanarchitecture.domain.repository.SubscriptionRepository;
import com.project.cleanarchitecture.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public UserDto createUser(UserCreateDto dto) throws Exception {
		try {
			userValidator.validateUserDto(dto);
			User user = userMapper.toModel(dto);
			User savedUser = userRepository.save(user);

			return userMapper.toDto(savedUser);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error on create User with DTO");
		}
	}

	@Override
	public UserDto updateUser(Long id, UserUpdateDto userDto) throws Exception {
		try {
			Optional<User> userOpt = userRepository.findById(id);
			User user = userOpt.get();
			userValidator.validateUserUpdateDto(userDto);
			user.setName(userDto.getName());
			User updatedUser = userRepository.save(user);
			return userMapper.toDto(updatedUser);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with id: " + id);
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
		return users.stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UserWithBalanceDto updateBalance(Long id, BigDecimal value) {
		User user = userRepository.findById(id).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found for id: " + id));

		if(value.compareTo(BigDecimal.ZERO) < 0) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Value invalid for user balance");
		
		user.addBalance(value);
		userRepository.save(user);
		
		return userMapper.toUserWithBalanceDto(user);
	}

	@Override
	public UserCoursesDto getCoursesByUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found for id: " + id));
		
		UserCoursesDto userCoursesDto = userMapper.toUserCoursesDto(user);
		
		Subscription userSubscription = subscriptionRepository.findByUserId(id);
		if(userSubscription == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User subscription not found for user name: " + userCoursesDto.getName());
			
		List<Course> courses = courseRepository.findByRole(userSubscription.getRole());
		
		List<CourseDto> coursesDto = new ArrayList<>();
		for (Course course : courses)
			coursesDto.add(courseMapper.toDto(course));
		
		userCoursesDto.setCourses(coursesDto);
		return userCoursesDto;
	}
}
