package org.stalesoft.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.stalesoft.model.Document;
import org.stalesoft.service.DocumentService;
import org.stalesoft.web.dto.DocumentListDto;
import org.stalesoft.web.dto.SearchDto;

@Controller
public class DocumentController {

	@Autowired
	DocumentService documentService;


	private static Logger log = LoggerFactory.getLogger(DocumentController.class);

	
	/**
	 * Displays empty
	 */
	@GetMapping("/app/document/{repository}/{category}")
	public String documentsHome(@PathVariable("fullContext") String repository, String category, Model model) {
		
		DocumentListDto documentList  = new DocumentListDto();
		documentList.setRepository(repository);
		documentList.setCategory(category);
		
		model.addAttribute("results", documentList);
		
		return "app/documents";
		
	}

	/**
	 * 
	 * Upload a document

	 */
	@GetMapping("/app/document")
	public String uploadDocumentHome(Model model) {
		
		return "app/upload";
	}
	
	@PostMapping("/app/document")
	public String uploadDocument(@RequestParam("file") MultipartFile uploadDocument, 
			@RequestParam("repository") String repository,
			@RequestParam("category") String category,
			@RequestParam("consumerId") String consumerId,
			@RequestParam("documentId") String documentId,
			@RequestParam("description") String description,
			@RequestParam("consumerName") String consumerName,
			Model model) {

		
		// TODO: Validate: e.g. uploadDocument != null, etc.
		//TODO: Validate fullContext is leaf
		
		log.debug("uploading a document");

		InputStream documentInputStream = null;
		
		try {
			
			documentInputStream = uploadDocument.getInputStream();
			
		} catch (IOException e) {
			// TODO Need to throw a WebApplicationValidationException
			e.printStackTrace();
		}

		Document document = new Document();
		
		document.setRepository(repository);
		document.setCategory(category);
		
		document.setInputStream(documentInputStream);
		document.setName(uploadDocument.getOriginalFilename());
		document.setConsumerId(consumerId);
		document.setDescription(description);
		document.setConsumerName(consumerName);
		document.setDocumentId(documentId);
		
		String mimeType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
		
		try {
			mimeType = Files.probeContentType(Paths.get(uploadDocument.getOriginalFilename()));
			
		} catch (IOException e) {
			log.debug("Trouble probing mime type : {} ", e.getMessage());
			mimeType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
		}
		
		
		document.setMimeType(mimeType);
		
		documentService.addDocument(repository, category, document);
		
		//Getting the results from the location the document was saved.
		ArrayList<Document> documents = documentService.getDocuments(repository, category);
		
		DocumentListDto documentList  = new DocumentListDto();
		documentList.add(documents);
		documentList.setRepository(repository);
		documentList.setCategory(category);
		
		//TODO need to externalize the attribute names
		model.addAttribute("results", documentList);
		
		return "app/upload";

	}

	/**
	 * Finds documents 
	 */
	@PostMapping("/app/document/search")
	public String searchDocuments(@ModelAttribute("search") SearchDto searchDto, Model model) {

		//TODO validation
		ArrayList<Document> documents = documentService.findDocuments(searchDto.getQuery());

		DocumentListDto documentList = new DocumentListDto();
		documentList.add(documents);
		documentList.setCategory(searchDto.getCategory());
		documentList.setRepository(searchDto.getRepository());

		model.addAttribute("results", documentList);

		return "app/documents";
	}
	
	/**
	 * //https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc
	 * //https://stackoverflow.com/questions/33087470/jackrabbit-file-storage
	 * //https://www.baeldung.com/convert-input-stream-to-array-of-bytes
	 * //https://stackoverflow.com/questions/44743317/how-to-create-a-dynamic-link-with-thymeleaf-and-spring-boot
	 */
	
	/**
	 * Downloads documents 
	 */
	@GetMapping("/app/document/download/{uuid}")
	public ResponseEntity<byte[]> downloadDocument(@PathVariable("uuid") String uuid,  Model model) {
		

		ResponseEntity<byte[]> response  = null;
		
		try {

			Document document = documentService.getDocument(uuid);

			byte[] binaryDocument;

			binaryDocument = new byte[document.getInputStream().available()];

			document.getInputStream().read(binaryDocument);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(document.getMimeType()));
			headers.setContentDispositionFormData(document.getName(), document.getName());
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

			response = new ResponseEntity<>(binaryDocument, headers, HttpStatus.OK);

		} catch (IOException e) {
			// TODO throw some PresentationException
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Get Document Details
	 */
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
