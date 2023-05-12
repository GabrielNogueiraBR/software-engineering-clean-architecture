package com.project.cleanarchitecture.application.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.cleanarchitecture.application.dto.SubscriptionCreateDto;
import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.model.User;

@Component
public class SubscriptionMapper {

	public Subscription toEntity(SubscriptionDto dto, User user) {
		Role role = dto.getRole();
		return new Subscription(user, role, dto.getStartDate(), dto.getEndDate());
	}

	public SubscriptionDto toDto(Subscription entity) {
		return new SubscriptionDto(entity.getId(), entity.getUser(), entity.getRole(), entity.getStartDate(),
				entity.getEndDate());
	}

	public List<Subscription> toEntityList(List<SubscriptionDto> dtos, User user) {
		return dtos.stream().map(dto -> toEntity(dto, user)).collect(Collectors.toList());
	}

	public List<SubscriptionDto> toDtoList(List<Subscription> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}

	public void updateEntityFromDto(SubscriptionDto subscriptionDto, Subscription subscription) {
		subscription.setUser(subscriptionDto.getUser());
		subscription.setRole(subscriptionDto.getRole());
		subscription.setStartDate(subscriptionDto.getStartDate());
		subscription.setEndDate(subscriptionDto.getEndDate());
	}

	public Subscription toEntity(SubscriptionCreateDto subscriptionDto, User user) {
		Role role = subscriptionDto.getRole();
		return new Subscription(user, role, subscriptionDto.getStartDate(), subscriptionDto.getEndDate());
	}
}
