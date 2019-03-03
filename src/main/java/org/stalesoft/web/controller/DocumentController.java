package org.stalesoft.web.controller;

import java.io.IOException;
import java.io.InputStream;
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
import org.stalesoft.model.Folder;
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

	/**
	 * 
	 * Saves uploaded documents.
	 * 
	 * @param uploadDocument
	 * @return
	 */
	
	@RequestMapping(value = "/app/document", method = RequestMethod.POST)
	public String uploadDocument(@RequestParam("file") MultipartFile uploadDocument, Model model) {
		//TODO Log all incoming parameters
		
		// TODO: Validate: e.g. uploadDocument != null, etc.

		InputStream documentInputStream = null;
		
		try {
			
			documentInputStream = uploadDocument.getInputStream();
			
		} catch (IOException e) {
			// TODO Need to throw a WebApplicationValidationException
			e.printStackTrace();
		}

		Document document = new Document();
		
		// TODO: should have other form parameters to fill out the document object.
		// for now just trying to get the file itself uploaded and saved.
	
		document.setInputStream(documentInputStream);
		document.setPath("testupload");
		document.setName("MyFirstDocument");
		document.setMimeType("jpg");
		
		documentService.addDocument(document);
		
		ArrayList<Document> documents = documentService.findDocuments(document.getPath());//find all the documents in the path
		
		DocumentListDto documentList  = new DocumentListDto();
		documentList.add(documents);
		
		//TODO need to externalize the attribute names
		model.addAttribute("results", documentList);
		
		return "app/search";

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
