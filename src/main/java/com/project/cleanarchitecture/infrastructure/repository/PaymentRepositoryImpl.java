package com.project.cleanarchitecture.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cleanarchitecture.domain.model.Payment;
import com.project.cleanarchitecture.domain.repository.PaymentRepository;

@Repository
public interface PaymentRepositoryImpl extends PaymentRepository, JpaRepository<Payment, Long> {
}