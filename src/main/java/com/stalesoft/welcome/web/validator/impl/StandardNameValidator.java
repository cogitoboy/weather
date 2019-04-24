package com.stalesoft.welcome.web.validator.impl;

import org.springframework.stereotype.Component;

import com.stalesoft.welcome.exception.ValidationException;
import com.stalesoft.welcome.web.validator.NameValidator;

@Component
public class StandardNameValidator implements NameValidator {

	@Override
	public void validateName(String firstName, String lastName) {
		
		if (firstName == null || "".equals(firstName )
				|| lastName == null || "".equals(lastName)) {
			throw new ValidationException("First and last name cannot be empty");
		}
		
	}

}
