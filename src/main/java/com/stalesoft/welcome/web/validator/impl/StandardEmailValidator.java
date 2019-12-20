package com.stalesoft.welcome.web.validator.impl;

import org.springframework.stereotype.Component;

import com.stalesoft.welcome.exception.ValidationException;
import com.stalesoft.welcome.web.validator.EmailValidator;


@Component
public class StandardEmailValidator implements EmailValidator {

	@Override
	public void validateEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new ValidationException("Email cannont be empty");
		}
		
	}

}
