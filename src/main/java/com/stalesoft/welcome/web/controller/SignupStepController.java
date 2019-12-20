package com.stalesoft.welcome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		String nextState = "error";
		
		try {
		
			if (signupDtoValidator.validate(signupDto)) {
			
				//create account
				
				nextState = "continue";	
			
		    }
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return nextState;
		
	}
	
	@ModelAttribute(value="signup")
	public SignupDto getSignupDto() {
		return  new SignupDto();
		
	}
	
}
