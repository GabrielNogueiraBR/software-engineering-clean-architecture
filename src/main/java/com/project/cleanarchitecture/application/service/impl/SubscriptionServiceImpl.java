package com.project.cleanarchitecture.application.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.project.cleanarchitecture.application.dto.RolePriceDto;
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
import com.project.cleanarchitecture.domain.repository.SubscriptionRepository;
import com.project.cleanarchitecture.domain.repository.UserRepository;
import com.project.cleanarchitecture.infrastructure.repository.SubscriptionRepositoryImpl;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private SubscriptionValidator subscriptionValidator;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentService paymentService;

	@Override
	public SubscriptionDto createSubscription(SubscriptionCreateDto subscriptionDto, Long user_id) {
		BigDecimal price = this.getPriceByRole(subscriptionDto.getRole());
		
		subscriptionValidator.validateDto(subscriptionDto);
		
		User user = userRepository.findById(user_id).orElseThrow(() -> new ValidationException("User not found"));
		
		Subscription userSubscription = subscriptionRepository.findByUserId(user_id);
		
		if(userSubscription != null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has subscription");
		
		BigDecimal userBalance = user.getBalance();
		BigDecimal userBalanceAfterPayment = userBalance.subtract(price);
    	if (userBalanceAfterPayment.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not have enough balance for subscription");
        }
    	
		Payment payment = paymentService.createPayment(price.doubleValue());

		if (!paymentService.isPaymentConfirmed(payment)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment not confirmed");
		}

		if (!paymentService.isPaymentAmountCorrect(price.doubleValue(), payment)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect payment amount.");
		}

		Subscription subscription = subscriptionMapper.toEntity(subscriptionDto, user);
		subscriptionRepository.save(subscription);
		
		payment = paymentService.updatePayment(payment, subscription);
		subscription.addPayment(payment);
		
		user.addBalance(price.negate());
		userRepository.save(user);

		return subscriptionMapper.toDto(subscription);
	}

	@Override
	@Transactional
	public SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) throws ValidationException {
		Subscription subscription = subscriptionRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription not found for id: " + id));

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
		Subscription subscription = subscriptionRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription not found for id: " + id));
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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role: " + role);
		}
	}

	@Override
	public List<RolePriceDto> getRolePrices() {
		List<RolePriceDto> rolePricesDto = new ArrayList<>();
		
		for (Role role : Role.values()) {
			RolePriceDto roleIteration = new RolePriceDto();
			roleIteration.setPrice(getPriceByRole(role));
			roleIteration.setRole(role);
			rolePricesDto.add(roleIteration);
		 }
		
		return rolePricesDto;
	}

}
