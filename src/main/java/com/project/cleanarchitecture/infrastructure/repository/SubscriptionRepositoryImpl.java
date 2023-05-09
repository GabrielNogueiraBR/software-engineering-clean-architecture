package com.project.cleanarchitecture.infrastructure.repository;

import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.repository.SubscriptionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepositoryImpl extends SubscriptionRepository, JpaRepository<Subscription, Long> {
}

