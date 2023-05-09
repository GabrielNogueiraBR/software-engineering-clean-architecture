package com.project.cleanarchitecture.domain.repository;

import com.project.cleanarchitecture.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}