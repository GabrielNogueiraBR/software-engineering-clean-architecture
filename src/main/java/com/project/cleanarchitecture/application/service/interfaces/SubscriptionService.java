package com.project.cleanarchitecture.application.service.interfaces;

import java.util.List;

import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;

public interface SubscriptionService {
    SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) throws ValidationException;
    SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) throws ValidationException;
    void deleteSubscription(Long id);
    SubscriptionDto getSubscriptionById(Long id);
    List<SubscriptionDto> getAllSubscriptions();
}
