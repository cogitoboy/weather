package com.stalesoft.welcome.web.validator.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stalesoft.welcome.exception.ValidationException;
import com.stalesoft.welcome.web.dto.SignupDto;
import com.stalesoft.welcome.web.validator.EmailValidator;
import com.stalesoft.welcome.web.validator.NameValidator;
import com.stalesoft.welcome.web.validator.SignupDtoValidator;


@Component
public class StandardSignupDtoValidator implements SignupDtoValidator{

	@Autowired
	EmailValidator emailValidator;
	
	@Autowired
	NameValidator nameValidator;
	
	@Override
	public Boolean validate(SignupDto signupDto) {
		
		ArrayList<String> errorMessages = new ArrayList<>();
		Boolean error = Boolean.FALSE;
		try {
			
			emailValidator.validateEmail(signupDto.getEmail());
			nameValidator.validateName(signupDto.getFirstName(), signupDto.getLastName());
			
		} catch (ValidationException e) {
			error = Boolean.TRUE;
			errorMessages.add(e.getMessage());
			
			signupDto.setError(error);
			signupDto.setErrorMessages(errorMessages);
			
		}
		
		
		return !error;
		
	}

	
	

}
