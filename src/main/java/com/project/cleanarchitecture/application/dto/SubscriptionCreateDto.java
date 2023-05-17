package com.project.cleanarchitecture.application.dto;

import com.project.cleanarchitecture.domain.model.Role;

public class SubscriptionCreateDto {

    private Role role;

    public SubscriptionCreateDto() {
    }

    public SubscriptionCreateDto(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}