package com.stalesoft.welcome.web.validator.impl;

import org.springframework.stereotype.Component;

import com.stalesoft.welcome.web.dto.SignupDto;
import com.stalesoft.welcome.web.validator.SignupDtoValidator;


@Component
public class StandardSignupDtoValidator implements SignupDtoValidator{

	@Override
	public SignupDto validate(SignupDto signupDto) {
		signupDto.setError(Boolean.TRUE);
		return signupDto;
		
	}

	
	

}
