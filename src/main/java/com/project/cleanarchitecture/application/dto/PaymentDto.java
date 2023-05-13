package com.project.cleanarchitecture.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDto {
    private Long id;
    private BigDecimal value;
    private LocalDateTime paymentDate;

    public PaymentDto() {}

    public PaymentDto(Long id, BigDecimal value, LocalDateTime paymentDate) {
        this.id = id;
    	this.value = value;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    } 
}
