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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import org.stalesoft.web.dto.SearchDto;

@Controller
public class DocumentController {

	@Autowired
	DocumentService documentService;

	private static Logger log = LoggerFactory.getLogger(DocumentController.class);

	
	@GetMapping("/app/document")
	public String home(Model model) {

		DocumentListDto documentList  = new DocumentListDto();
		
		model.addAttribute("results", documentList);
		
		return "app/documents";
		
	}

	/**
	 * 
	 * Saves uploaded documents.
	 * 
	 * @param uploadDocument
	 * @return
	 */
	
	//RequestMapping(value = "/app/document", method = RequestMethod.POST)
	@PostMapping("/app/document")
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
		
		// TODO: a complete set of document parameters.
		
		document.setInputStream(documentInputStream);
		document.setPath("testupload");
		document.setName(uploadDocument.getOriginalFilename());
		
		//Extract the mimetype  //TODO utitlity method
		String mimeType = uploadDocument.getOriginalFilename();
		mimeType = mimeType.substring(mimeType.lastIndexOf(".") + 1);
		mimeType = mimeType.toLowerCase();
		
		document.setMimeType(mimeType);
		
		documentService.addDocument(document);
		//Getting the results from the location the document was saved.
		ArrayList<Document> documents = documentService.findDocuments(document.getPath());
		
		DocumentListDto documentList  = new DocumentListDto();
		documentList.add(documents);
		
		//TODO need to externalize the attribute names
		model.addAttribute("results", documentList);
		
		return "app/documents";

	}

	@PostMapping("/app/document/search")
	public String searchDocuments(@ModelAttribute("search") SearchDto searchDto, Model model) {

		//TODO validation
		//TODO search via wild cards
		//TODO search all attributes
		ArrayList<Document> documents = documentService.findDocuments(searchDto.getQuery());

		DocumentListDto documentList = new DocumentListDto();
		documentList.add(documents);

		model.addAttribute("results", documentList);

		return "app/documents";
	}

	@GetMapping("/app/document/detail")
	public String details(Model model) {

		log.debug("Home requested for appName");

		String documentId = "123";

		documentService.getDocument(documentId);

		return "app/document";
	}
	
	@ModelAttribute(value="search")
	public SearchDto getSearchDto() {
		return new SearchDto();
	}
}
