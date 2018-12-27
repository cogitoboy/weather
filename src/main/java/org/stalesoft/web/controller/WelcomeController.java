package org.stalesoft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	
	@GetMapping("/")
    public String home(Model model) {
       
        return "index";
    }
	
	@GetMapping("/welcome")
    public String greeting(Model model) {
       
        return "welcome";
    }
}
