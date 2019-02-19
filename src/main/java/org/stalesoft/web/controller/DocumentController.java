package org.stalesoft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {

	
	@GetMapping("/app/document")
    public String home(Model model) {
       
        return "app/document";
    }
}
