package com.project.cleanarchitecture.application.dto;

import com.project.cleanarchitecture.domain.model.Role;

public class CourseDto {


    private Long id;
    private String title;
    private Role role;

    public CourseDto() {}

    public CourseDto(Long id, String title, Role role) {
        this.id = id;
        this.title = title;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
