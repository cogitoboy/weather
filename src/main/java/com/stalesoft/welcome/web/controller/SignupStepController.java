package com.stalesoft.welcome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stalesoft.welcome.web.dto.SignupDto;
import com.stalesoft.welcome.web.validator.SignupDtoValidator;

@Controller
@RequestMapping("signup")
public class SignupStepController {

	private static Logger log = LoggerFactory.getLogger(SignupStepController.class);
	
	@Autowired
	SignupDtoValidator signupDtoValidator;
	
	@GetMapping("/")
	public String getHome() {
		
		return "/signup/signup";
	
	}
	
	public String createAccount(SignupDto signupDto) {
		
		log.debug("createAccount called");
		
		signupDto = signupDtoValidator.validate(signupDto);
	
		if (signupDto.getError()) {
			
			return "error";
			
		} else {
			
			return "continue";	
			
		}
		
	}
	
	@ModelAttribute(value="signup")
	public SignupDto getSignupDto() {
		return  new SignupDto();
		
	}
	
}
