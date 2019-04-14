package com.stalesoft.welcome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stalesoft.web.dto.SearchDto;

import com.stalesoft.welcome.web.dto.SignupDto;

@Controller
@RequestMapping("signup")
public class SignupController {

	private static Logger log = LoggerFactory.getLogger(SignupController.class);
	

	@GetMapping("/")
	public String getHome() {
		
		return "/signup/signup";
	
	}
	
	public String createAccount(@ModelAttribute("signup") SignupDto signupDto, Model model) {
		
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
	
	public Model getSignupModel(SignupDto signupDto) {
		Model model =  new ExtendedModelMap();
		
		model.addAttribute("signup", signupDto);
		return model;
	}
}
