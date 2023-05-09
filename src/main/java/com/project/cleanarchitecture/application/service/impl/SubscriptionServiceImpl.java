package com.project.cleanarchitecture.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.application.service.interfaces.SubscriptionService;
import com.project.cleanarchitecture.application.validator.SubscriptionValidator;
import com.project.cleanarchitecture.application.factory.SubscriptionMapper;
import com.project.cleanarchitecture.domain.exception.ValidationException;
import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.repository.SubscriptionRepository;
import com.project.cleanarchitecture.domain.repository.UserRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final SubscriptionMapper subscriptionMapper;
	private final SubscriptionValidator subscriptionValidator;
	private final UserRepository userRepository;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper,
			SubscriptionValidator subscriptionValidator, UserRepository userRepository) {
		this.subscriptionRepository = subscriptionRepository;
		this.subscriptionMapper = subscriptionMapper;
		this.subscriptionValidator = subscriptionValidator;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) throws ValidationException {
		subscriptionValidator.validateDto(subscriptionDto);
		User user = userRepository.findById(subscriptionDto.getUser().getId())
				.orElseThrow(() -> new ValidationException("User not found"));
		Subscription subscription = subscriptionMapper.toEntity(subscriptionDto, user);
		subscriptionRepository.save(subscription);
		return subscriptionMapper.toDto(subscription);
	}

	@Override
	@Transactional
	public SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) throws ValidationException {
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Subscription not found for id: " + id));

		subscriptionValidator.validateUpdateDto(subscriptionDto);

		subscriptionMapper.updateEntityFromDto(subscriptionDto, subscription);
		Subscription updatedSubscription = subscriptionRepository.save(subscription);
		return subscriptionMapper.toDto(updatedSubscription);
	}

	@Override
	@Transactional
	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public SubscriptionDto getSubscriptionById(Long id) {
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Subscription not found for id: " + id));
		return subscriptionMapper.toDto(subscription);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubscriptionDto> getAllSubscriptions() {
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		return subscriptions.stream().map(subscriptionMapper::toDto).collect(Collectors.toList());
	}
}
