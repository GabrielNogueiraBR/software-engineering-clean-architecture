package com.project.cleanarchitecture.application.dto;

import java.math.BigDecimal;

import com.project.cleanarchitecture.domain.model.Role;

public class RolePriceDto {
	
	private Role role;
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
}
