package com.project.cleanarchitecture.application.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.cleanarchitecture.application.dto.PaymentDto;
import com.project.cleanarchitecture.domain.model.Payment;

@Component
public class PaymentMapper {

	public PaymentDto toDto(Payment payment) {
		PaymentDto dto = new PaymentDto();
		dto.setId(payment.getId());
		dto.setValue(payment.getValue());
		dto.setPaymentDate(payment.getPaymentDate());

		return dto;
	}

	public List<PaymentDto> toDtoList(List<Payment> entities) {
		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}
}
