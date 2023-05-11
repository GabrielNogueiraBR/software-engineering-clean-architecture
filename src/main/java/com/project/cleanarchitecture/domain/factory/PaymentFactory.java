package com.project.cleanarchitecture.domain.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.cleanarchitecture.domain.model.Payment;
import com.project.cleanarchitecture.domain.model.Subscription;

public class PaymentFactory {
	private Payment payment;
	
	public PaymentFactory withSubscriptionAndPrice(Subscription subscription, BigDecimal price) {
		LocalDateTime currentDate = LocalDateTime.now();
		this.payment = new Payment(subscription, price, currentDate);
		return this;
	}
	
	public Payment create() {
		return this.payment;
	}
}
