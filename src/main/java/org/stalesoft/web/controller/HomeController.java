package org.stalesoft.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.stalesoft.data.impl.JcrDocumentDao;

@Controller
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/app/{appName}/home")
    public String home(Model model, @PathVariable("appName") String appName) {
		
       log.debug("Home requested for appName : {}", appName);
		
        return "app/home";
    }
	
}
