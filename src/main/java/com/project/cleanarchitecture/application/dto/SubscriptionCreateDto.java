package com.project.cleanarchitecture.application.dto;

import java.math.BigDecimal;

import com.project.cleanarchitecture.domain.model.Role;

public class SubscriptionCreateDto {

    private Role role;
    private BigDecimal paymentValue;

    public SubscriptionCreateDto() {
    }

    public SubscriptionCreateDto(Role role, BigDecimal paymentValue) {
        this.role = role;
        this.paymentValue = paymentValue;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getPaymentValue() {
        return paymentValue;
    }
    
    public void setPaymentValue(BigDecimal paymentValue) {
        this.paymentValue = paymentValue;
    }
}