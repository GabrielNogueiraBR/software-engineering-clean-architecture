	package com.project.cleanarchitecture.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<Payment>();
	
	private Boolean isActive = true;

	private LocalDate startDate;

	private LocalDate endDate;

	public Subscription(User user, Role role, LocalDate startDate, LocalDate endDate) {
		this.user = user;
		this.role = role;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	protected Subscription() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void deactivateSubscription() {
		this.isActive = false;
	}
	
	public Boolean getIsActive() {
		return this.isActive;
	}
	
	public List<Payment> getPayments(){
		return this.payments;
	}
	
	public boolean addPayment(Payment payment) {
		return this.payments.add(payment);
	}
}
