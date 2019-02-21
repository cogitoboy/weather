package org.stalesoft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	

	@GetMapping("/app/home")
    public String home(Model model) {
       
        return "app/home";
    }
	
}
