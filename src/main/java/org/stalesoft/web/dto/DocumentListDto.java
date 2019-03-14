package org.stalesoft.web.dto;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public class DocumentListDto implements BaseDto<ArrayList<Document>>{
	
	private String repository;
	private String category;
	private ArrayList<DocumentDto> documentDtos = new ArrayList<>();

	public ArrayList<DocumentDto> getDocuments() {
		return documentDtos;
	}

	public void setDocuments(ArrayList<DocumentDto> documentDtos) {
		
		assert(documentDtos != null);
		
		this.documentDtos = documentDtos;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	@Override
	public void add(ArrayList<Document> documents) {
		
		assert(documents != null);
		
		for(Document document : documents) {
			
			DocumentDto documentDto = new DocumentDto();
			documentDto.add(document);
			
			this.documentDtos.add(documentDto);
			
		}
		
	}

	

}
