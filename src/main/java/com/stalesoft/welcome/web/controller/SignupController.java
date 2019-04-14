package com.stalesoft.welcome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stalesoft.welcome.web.dto.SignupDto;

@Controller
@RequestMapping("signup")
public class SignupController {

	private static Logger log = LoggerFactory.getLogger(SignupController.class);
	

	@GetMapping("/")
	public String getHome() {
		
		return "/signup/signup";
	
	}
	
	@PostMapping("/")
	public String createAccount(@ModelAttribute("signup") SignupDto signupDto, Model model) {
		
		log.debug("Emails is {}", signupDto.getEmail());
		log.debug("Password is {}", signupDto.getPassword());

		return "/signup/signup-done";
	}
	
	
	@ModelAttribute(value="signup")
	public SignupDto getSignupDto() {
		return  new SignupDto();
		
	}
	
	public Model getSignupModel(SignupDto signupDto) {
		Model model =  new ExtendedModelMap();
		
		model.addAttribute("signup", signupDto);
		return model;
	}
}
