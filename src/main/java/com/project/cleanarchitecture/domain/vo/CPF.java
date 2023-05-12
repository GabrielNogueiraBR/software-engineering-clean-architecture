package com.project.cleanarchitecture.domain.vo;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CPF {
	@Column(name = "cpf")
	private String value;
	private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

	public CPF() {
	}

	public CPF(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("CPF inválido.");
		}
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private boolean isValid(String value) {
		return CPF_PATTERN.matcher(value).matches();
	}
}
