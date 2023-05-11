package com.project.cleanarchitecture.application.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;
import com.project.cleanarchitecture.domain.model.Role;

public interface SubscriptionService {
	SubscriptionDto createSubscription(SubscriptionDto subscriptionDto, BigDecimal price, BigDecimal paymentValue) throws ValidationException;
    SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) throws ValidationException;
    void deleteSubscription(Long id);
    SubscriptionDto getSubscriptionById(Long id);
    List<SubscriptionDto> getAllSubscriptions();
    BigDecimal getPriceByRole(Role role);
}