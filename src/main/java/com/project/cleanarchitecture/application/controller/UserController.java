package com.project.cleanarchitecture.application.controller;

import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.UserUpdateDto;
import com.project.cleanarchitecture.application.service.interfaces.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "Create a new user")
    public ResponseEntity<UserDto> createUser(
            @ApiParam(value = "User data", required = true) @RequestBody UserCreateDto userCreateDto) throws Exception {
        UserDto userDto = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing user")
    public ResponseEntity<UserDto> updateUser(
            @ApiParam(value = "User id", required = true) @PathVariable Long id,
            @ApiParam(value = "User data", required = true) @RequestBody UserUpdateDto userUpdateDto) throws Exception {
        UserDto userDto = userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an existing user")
    public ResponseEntity<Void> deleteUser(
            @ApiParam(value = "User id", required = true) @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a user by id")
    public ResponseEntity<UserDto> getUserById(
            @ApiParam(value = "User id", required = true) @PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping
    @ApiOperation(value = "List all users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok().body(userDtos);
    }

}
