package com.project.cleanarchitecture.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.project.cleanarchitecture.domain.model.Role;

public class SubscriptionCreateDto {

    private Long id;
    private Role role;
    private BigDecimal paymentValue;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionCreateDto() {
    }

    public SubscriptionCreateDto(Long id, Role role, LocalDate startDate, LocalDate endDate) {
        this.id = id;
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