package com.project.cleanarchitecture.application.dto;

import java.time.LocalDate;
import java.util.List;

import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.User;

public class SubscriptionDto {

    private Long id;
    private UserDto user;
    private Role role;
    private List<PaymentDto> payments;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Long id, UserDto user, Role role, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.user = user;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public List<PaymentDto> getPayments() {
        return this.payments;
    }
    
    public void setPayments(List<PaymentDto> payments) {
        this.payments = payments;
    }
}
