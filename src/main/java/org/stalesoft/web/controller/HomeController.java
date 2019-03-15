package org.stalesoft.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.stalesoft.web.dto.SearchDto;

@Controller
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/app/{repository}/home")
	public String typeHome(Model model, @PathVariable("repository") String repository) {

		log.debug("Category List reqeuested for  repository : {}", repository);

		return "app/catagories";
	}

	@GetMapping("/app/home")
	public String sourceHome(Model model) {

		log.debug("Repository list requested");

		return "app/repositories";
	}
	
	@ModelAttribute(value="search")
	public SearchDto getSearchDto() {
		return new SearchDto();
	}

}
