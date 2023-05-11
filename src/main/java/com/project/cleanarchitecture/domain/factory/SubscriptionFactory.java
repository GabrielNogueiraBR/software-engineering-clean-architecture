package com.project.cleanarchitecture.domain.factory;

import java.time.LocalDate;

import com.project.cleanarchitecture.domain.model.Role;
import com.project.cleanarchitecture.domain.model.Subscription;
import com.project.cleanarchitecture.domain.model.User;

public class SubscriptionFactory {
	private Subscription subscription;
	
	public SubscriptionFactory withUserAndRole(User user,Role role) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);
		this.subscription = new Subscription(user, role, startDate, endDate);
		
		return this;
	} 
	
	public Subscription create() {
		return this.subscription;
	}
}
