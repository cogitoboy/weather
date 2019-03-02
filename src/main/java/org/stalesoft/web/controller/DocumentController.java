package org.stalesoft.web.controller;

import java.util.ArrayList;

import org.apache.tomcat.jni.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.stalesoft.model.Document;
import org.stalesoft.service.DocumentService;
import org.stalesoft.web.dto.DocumentDto;
import org.stalesoft.web.dto.DocumentListDto;


@Controller
public class DocumentController {

	@Autowired
	DocumentService documentService;
	
	
	private static Logger log = LoggerFactory.getLogger(DocumentController.class);
	
	
	@GetMapping("/app/document")
    public String home(Model model) {
		
		log.debug("Home requested for appName");
	
		
        return "app/document";
    }

	
	//Need to use a rest client to call this method!
	//Need to locate and post a local pdf document.
	
	
	@RequestMapping(value="/app/document", method=RequestMethod.POST)
	public @ResponseBody String addDocument(@RequestParam("document") MultipartFile document) {
		String returnMessage = "uploaded";
		
		if (!document.isEmpty()) {
			
		}
		
		return returnMessage;
		
		
	}
	@GetMapping("/app/document/search")
    public String search(Model model) {
		
		log.debug("Home requested for appName");
	
		String query = "123";
		
		
		ArrayList<Document> documents = documentService.findDocuments(query);
		
		DocumentListDto documentList = new DocumentListDto();
		documentList.add(documents);
		
		model.addAttribute("results", documentList);
		
        return "app/search";
    }
	
	@GetMapping("/app/document/detail")
    public String details(Model model) {
		
		log.debug("Home requested for appName");
	
		String documentId = "123";
		
		documentService.getDocument(documentId);
		
        return "app/document";
    }
}
