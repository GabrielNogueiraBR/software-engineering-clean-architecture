package com.project.cleanarchitecture.application.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cleanarchitecture.application.dto.SubscriptionDto;
import com.project.cleanarchitecture.application.service.interfaces.SubscriptionService;
import com.project.cleanarchitecture.domain.model.Role;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/subscriptions")
@Api(value = "Courses API", tags = "Courses")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping("/{id}")
	public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable Long id) {
		SubscriptionDto subscription = subscriptionService.getSubscriptionById(id);
		return ResponseEntity.ok(subscription);
	}

	@GetMapping
	public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
		List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptions();
		return ResponseEntity.ok(subscriptions);
	}

	@GetMapping("/price/{role}")
	public ResponseEntity<BigDecimal> getPriceByRole(@PathVariable Role role) {
		BigDecimal price = subscriptionService.getPriceByRole(role);
		return ResponseEntity.ok(price);
	}
}
