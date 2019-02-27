package org.stalesoft.web.dto;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public class DocumentListDto implements BaseDto<ArrayList<Document>>{
	
	private ArrayList<DocumentDto> documentDtos = new ArrayList<>();

	public ArrayList<DocumentDto> getDocuments() {
		return documentDtos;
	}

	public void setDocuments(ArrayList<DocumentDto> documentDtos) {
		
		assert(documentDtos != null);
		
		this.documentDtos = documentDtos;
	}

	@Override
	public void add(ArrayList<Document> model) {
		

		for(Document document : model) {
			
			DocumentDto documentDto = new DocumentDto();
			documentDto.add(document);
			
			this.documentDtos.add(documentDto);
			
		}
		
	}
	
	

}
