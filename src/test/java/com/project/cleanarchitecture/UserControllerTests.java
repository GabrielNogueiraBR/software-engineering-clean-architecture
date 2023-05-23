package com.project.cleanarchitecture;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.project.cleanarchitecture.application.controller.UserController;
import com.project.cleanarchitecture.application.dto.UserCreateDto;
import com.project.cleanarchitecture.application.dto.UserDto;
import com.project.cleanarchitecture.application.dto.CourseDto;
import com.project.cleanarchitecture.application.dto.UserCoursesDto;
import com.project.cleanarchitecture.application.service.interfaces.SubscriptionService;
import com.project.cleanarchitecture.application.service.interfaces.UserService;
import com.project.cleanarchitecture.domain.model.*;
import com.project.cleanarchitecture.domain.factory.SubscriptionFactory;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class) 
public class UserControllerTests {
	
	@MockBean
    private UserService userService;

	@MockBean
    private SubscriptionService subscriptionService;
	
	@Autowired
    private MockMvc mockMvc;
		
	@Test
    public void userMustBeCreated() throws Exception {

		UserDto userDto = new UserDto("user_test", "user@email.com", "111.111.111-11");
		userDto.setId(1L);
		
		when(userService.createUser(any(UserCreateDto.class))).thenReturn(userDto);
		
        mockMvc
            .perform(post("/api/users")
            		.contentType("application/json")
                    .content("{\r\n"
                    		+ "  \"cpf\": \"111.111.111-11\",\r\n"
                    		+ "  \"email\": \"user@email.com\",\r\n"
                    		+ "  \"name\": \"user_test\",\r\n"
                    		+ "  \"password\": \"12345\"\r\n"
                    		+ "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value("1")) 
                    .andExpect(jsonPath("$.name").value("user_test"))
                    .andExpect(jsonPath("$.email").value("user@email.com"))
                    .andExpect(jsonPath("$.cpf").value("111.111.111-11")); 
    }
	
    @Test
    public void testGetCoursesByUser() throws Exception {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setName("User");

        CourseDto course1 = new CourseDto();
        course1.setId(1L);
        course1.setTitle("Course 1");
        course1.setRole(Role.BASIC_SUBSCRIBER);

        CourseDto course2 = new CourseDto();
        course2.setId(2L);
        course2.setTitle("Course 2");
        course2.setRole(Role.BASIC_SUBSCRIBER);

        List<CourseDto> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        UserCoursesDto expectedResponse = new UserCoursesDto();
        expectedResponse.setName(user.getName());
        expectedResponse.setCourses(courses);

        when(userService.getCoursesByUser(userId)).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/users/{id}/courses", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.courses.length()").value(courses.size()))
                .andExpect(jsonPath("$.courses[0].id").value(course1.getId()))
                .andExpect(jsonPath("$.courses[0].title").value(course1.getTitle()))
                .andExpect(jsonPath("$.courses[1].id").value(course2.getId()))
                .andExpect(jsonPath("$.courses[1].title").value(course2.getTitle()));
    }
	
}
