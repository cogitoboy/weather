package org.stalesoft.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.stalesoft.model.Document;
import org.stalesoft.service.DocumentService;

import org.stalesoft.web.dto.DocumentListDto;
import org.stalesoft.web.dto.SearchDto;


//TODO: Add console logging.

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

	 */
	
	@PostMapping("/app/document")
	public String uploadDocument(@RequestParam("file") MultipartFile uploadDocument, Model model) {
		//TODO Log all incoming parameters
		
		// TODO: Validate: e.g. uploadDocument != null, etc.
		
		log.debug("uploading a document");

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
		
		
		String mimeType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
		
		try {
			mimeType = Files.probeContentType(Paths.get(uploadDocument.getOriginalFilename()));
			
		} catch (IOException e) {
			log.debug("Trouble probing mime type : {} ", e.getMessage());
			mimeType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
		}
		
		
		document.setMimeType(mimeType);
		
		String uuid = documentService.addDocument(document);
		
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
		ArrayList<Document> documents = documentService.findDocuments(searchDto.getQuery());

		DocumentListDto documentList = new DocumentListDto();
		documentList.add(documents);

		model.addAttribute("results", documentList);

		return "app/documents";
	}
	
	@GetMapping("/app/document/download")
	public ResponseEntity<byte[]> downloadDocument(Model model) {
		//https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc
		//https://stackoverflow.com/questions/33087470/jackrabbit-file-storage
		//https://www.baeldung.com/convert-input-stream-to-array-of-bytes
		Document document = null;
		document.getInputStream()
		
		
		byte[] binaryDocument = new byte[document.getInputStream().available()];
		document.getInputStream().read(binaryDocument);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(document.getMimeType()));
		headers.setContentDispositionFormData(document.getName(), document.getName());
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		 
		ResponseEntity<byte[]> response = new ResponseEntity<>(binaryDocument, headers, HttpStatus.OK);
		
		return response;
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
