package com.project.cleanarchitecture.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cleanarchitecture.domain.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
