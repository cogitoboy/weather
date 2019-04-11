package com.stalesoft.welcome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.stalesoft.web.controller.HomeController;

@Controller
@RequestMapping("welcome")
public class WelcomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/home")
	public String getHome() {
		return "/welcome/home";
	}
	
}
