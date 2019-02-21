package org.stalesoft.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DocumentController {

	private static Logger log = LoggerFactory.getLogger(DocumentController.class);
	
	@GetMapping("/app/document")
    public String home(Model model) {
		log.debug("Home requested for appName");
			
        return "app/document";
    }
}
