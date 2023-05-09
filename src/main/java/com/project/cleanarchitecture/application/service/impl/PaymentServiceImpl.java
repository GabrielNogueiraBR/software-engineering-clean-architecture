package com.project.cleanarchitecture.application.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cleanarchitecture.application.service.interfaces.PaymentService;
import com.project.cleanarchitecture.domain.model.Payment;
import com.project.cleanarchitecture.domain.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment createPayment(Double value) {
	    Payment payment = new Payment();
	    payment.setValue(BigDecimal.valueOf(value));
	    payment.setPaymentDate(LocalDateTime.now());
	    return paymentRepository.save(payment);
	}

	@Override
	public boolean isPaymentConfirmed(Payment payment) {
	    Optional<Payment> optionalPayment = paymentRepository.findById(payment.getId());
	    return optionalPayment.isPresent();
	}

	@Override
	public boolean isPaymentAmountCorrect(Double value, Payment payment) {
	    return payment.getValue().compareTo(BigDecimal.valueOf(value)) == 0;
	}

}

