package org.stalesoft.web.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@GetMapping("/app/document/search")
    public String search(Model model) {
		
		log.debug("Home requested for appName");
	
		String documentId = "123";
		
		ArrayList<Document> documents = documentService.findDocuments(documentId);
		
		DocumentListDto documentList = new DocumentListDto();
		documentList.add(documents);
		
		
		//model.addAttribute("results", documentList);
		
		
		//TEST
		DocumentListDto testDocumentList = new DocumentListDto();
		
		Document document = new Document();
		document.setTitle("Title");
		document.setType(1);
		
		ArrayList<Document> testDocumentArray = new ArrayList<>();
		testDocumentArray.add(document);
		
		testDocumentList.add(testDocumentArray);
		
		model.addAttribute("results", testDocumentList);
		//END TEST
		
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
