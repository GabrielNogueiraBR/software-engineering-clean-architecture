package com.project.cleanarchitecture.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.User;

public class SubscriptionDto {

    private Long id;
    private User user;
    private Role role;
    private BigDecimal paymentValue;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Long id, User user, Role role, LocalDate startDate, LocalDate endDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
    
    public BigDecimal getPaymentValue() {
        return paymentValue;
    }
    
    public void setPaymentValue(BigDecimal paymentValue) {
        this.paymentValue = paymentValue;
    }
}
