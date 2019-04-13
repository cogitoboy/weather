package com.stalesoft.welcome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.stalesoft.welcome.web.dto.SignupDto;

@Controller
public class SignupController {

	private static Logger log = LoggerFactory.getLogger(SignupController.class);
	
	public String createAccount(SignupDto signupDto, Model model) {
		
		//validation
		
		//create account
		
		//respond
		
		log.debug("Emails is {}", signupDto.getEmail());
		
		return "continue";
	}
	
	@ModelAttribute(value="signup")
	public SignupDto getSignupDto() {
		return new SignupDto();
	}
}
