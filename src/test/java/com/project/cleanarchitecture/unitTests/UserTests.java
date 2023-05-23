package com.project.cleanarchitecture.unitTests;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.project.cleanarchitecture.domain.factory.UserFactory;
import com.project.cleanarchitecture.domain.model.User;

public class UserTests {
	
	@Rule
    public ExpectedException exceptionRule = ExpectedException.none();
	   
	
	@Test
	public void validateInvalidUserEmail() {
		exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid email.");
        
		User user = new UserFactory()
				.withEmailCPF("user", "email", "123.123.123-01", "12345")
				.create();
	}
	
	@Test
	public void validateInvalidUserCPF() {
		exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid CPF.");
        
		User user = new UserFactory()
				.withEmailCPF("user", "user@gmail.com", "1", "12345")
				.create();
	}
	
	@Test
	public void userBalanceMustBeUpdated() {
        BigDecimal balanceUpdate = new BigDecimal("10");
        
		User user = new UserFactory()
				.withEmailCPF("user", "user@gmail.com", "123.123.123-01", "12345")
				.create();
		
		user.addBalance(balanceUpdate);
		
		assertEquals(user.getBalance(), balanceUpdate);
	}
	
	@Test
	public void userBalanceMustNotBeUpdated() {
		exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Balance cannot be negative");
        
        BigDecimal balanceUpdate = new BigDecimal("-5000");
        
		User user = new UserFactory()
				.withEmailCPF("user", "user@gmail.com", "123.123.123-01", "12345")
				.create();
		
		user.addBalance(balanceUpdate);
	}
	
}
