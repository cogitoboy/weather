package com.stalesoft.welcome.web.validator;

import com.stalesoft.welcome.web.dto.SignupDto;

public interface SignupDtoValidator {
	
	public SignupDto validate(SignupDto signupDto);

}
