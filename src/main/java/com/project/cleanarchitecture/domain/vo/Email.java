package com.project.cleanarchitecture.domain.vo;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {
	@Column(name = "email")
	private String value;
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

	public Email() {
	}

	public Email(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("Invalid email.");
		}
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private boolean isValid(String value) {
		return EMAIL_PATTERN.matcher(value).matches();
	}
}
