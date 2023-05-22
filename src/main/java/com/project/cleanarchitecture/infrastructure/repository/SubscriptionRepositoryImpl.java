package com.project.cleanarchitecture.infrastructure.repository;

import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.repository.SubscriptionRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepositoryImpl extends SubscriptionRepository {
	
	@Query("SELECT s FROM Subscription s WHERE s.user.id = :userId AND CURRENT_DATE BETWEEN s.startDate AND endDate")
	public Subscription findByUserId(@Param("userId") Long userId);
}
