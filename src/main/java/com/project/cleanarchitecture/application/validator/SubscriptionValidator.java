package com.project.cleanarchitecture.application.validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.cleanarchitecture.application.dto.SubscriptionCreateDto;
import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.domain.exception.ValidationException;
import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.model.User;

@Component
public class SubscriptionValidator {

    public void validateCreate(Subscription subscription) throws ValidationException {
        if (subscription == null) {
            throw new ValidationException("Subscription cannot be null.");
        }

        User user = subscription.getUser();
        if (user == null) {
            throw new ValidationException("User cannot be null.");
        }

        Role role = subscription.getRole();
        if (role == null) {
            throw new ValidationException("Role cannot be null.");
        }

        LocalDate startDate = subscription.getStartDate();
        if (startDate == null) {
            throw new ValidationException("Start date cannot be null.");
        }

        LocalDate endDate = subscription.getEndDate();
        if (endDate == null) {
            throw new ValidationException("End date cannot be null.");
        }

        if (endDate.isBefore(startDate)) {
            throw new ValidationException("End date must be after start date.");
        }
    }

    public void validateUpdate(Subscription subscription) throws ValidationException {
        validateCreate(subscription);

        Long id = subscription.getId();
        if (id == null) {
            throw new ValidationException("Subscription id cannot be null.");
        }
    }

    public void validateDelete(Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("Subscription id cannot be null.");
        }
    }
    
    public void validateDto(SubscriptionCreateDto subscriptionDto) throws ValidationException {
        List<String> errors = new ArrayList<>();

        if (subscriptionDto.getRole() == null) {
            errors.add("Role is required");
        }

        if (subscriptionDto.getStartDate() == null) {
            errors.add("Start date is required");
        }

        if (subscriptionDto.getEndDate() == null) {
            errors.add("End date is required");
        }

        if (subscriptionDto.getStartDate() != null && subscriptionDto.getEndDate() != null
                && subscriptionDto.getStartDate().isAfter(subscriptionDto.getEndDate())) {
            errors.add("Start date cannot be after end date");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException("Subscription validation error");
        }
    }

    
    public void validateUpdateDto(SubscriptionDto subscriptionDto) throws ValidationException {
        if (subscriptionDto == null) {
            throw new ValidationException("Subscription cannot be null");
        }
        if (subscriptionDto.getId() == null) {
            throw new ValidationException("Subscription id cannot be null");
        }
        validateDto(subscriptionDto);
    }


}
