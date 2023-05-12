package com.project.cleanarchitecture.application.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.cleanarchitecture.application.dto.SubscriptionCreateDto;
import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.application.service.interfaces.PaymentService;
import com.project.cleanarchitecture.application.service.interfaces.SubscriptionService;
import com.project.cleanarchitecture.application.validator.SubscriptionValidator;
import com.project.cleanarchitecture.application.factory.SubscriptionMapper;
import com.project.cleanarchitecture.domain.exception.ValidationException;
import com.project.cleanarchitecture.domain.model.Payment;
import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.repository.UserRepository;
import com.project.cleanarchitecture.infrastructure.repository.SubscriptionRepositoryImpl;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private SubscriptionValidator subscriptionValidator;

	@Autowired
	private SubscriptionRepositoryImpl subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentService paymentService;

	@Override
	public SubscriptionDto createSubscription(SubscriptionCreateDto subscriptionDto, Long user_id) {
		BigDecimal price = this.getPriceByRole(subscriptionDto.getRole());
		BigDecimal paymentValue = subscriptionDto.getPaymentValue();
		
		subscriptionValidator.validateDto(subscriptionDto);
		User user = userRepository.findById(user_id)
				.orElseThrow(() -> new ValidationException("User not found"));

		Payment payment = paymentService.createPayment(paymentValue.doubleValue());

		if (!paymentService.isPaymentConfirmed(payment)) {
			throw new RuntimeException("Payment not confirmed");
		}

		if (!paymentService.isPaymentAmountCorrect(price.doubleValue(), payment)) {
			throw new RuntimeException("Incorrect payment amount");
		}

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
	
	@Override
	public BigDecimal getPriceByRole(Role role) {
	    switch (role) {
	        case GUEST:
	            return BigDecimal.ZERO;
	        case BASIC_SUBSCRIBER:
	            return new BigDecimal("9.99");
	        case PREMIUM_SUBSCRIBER:
	            return new BigDecimal("19.99");
	        default:
	            throw new IllegalArgumentException("Invalid Role: " + role);
	    }
	}

}
