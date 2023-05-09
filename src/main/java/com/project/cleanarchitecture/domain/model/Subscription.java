package com.project.cleanarchitecture.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  private LocalDate startDate;

  private LocalDate endDate;

  public Subscription(User user, Role role, LocalDate startDate, LocalDate endDate) {
    this.user = user;
    this.role = role;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  protected Subscription() {}

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Role getRole() {
    return role;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }
}
