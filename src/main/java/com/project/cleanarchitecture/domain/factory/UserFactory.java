package com.project.cleanarchitecture.domain.factory;

import com.project.cleanarchitecture.domain.model.User;
import com.project.cleanarchitecture.domain.vo.CPF;
import com.project.cleanarchitecture.domain.vo.Email;

public class UserFactory {
	private User user;
	
	public UserFactory withEmailCPF(String name, String email, String cpf, String password) {
		this.user = new User(name,new Email(email),new CPF(cpf), password);
		return this;
	}
	
	public User create() {
		return this.user;
	}
}
