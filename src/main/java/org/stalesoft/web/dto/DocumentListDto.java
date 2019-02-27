package org.stalesoft.web.dto;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public class DocumentListDto implements BaseDto<ArrayList<Document>>{
	
	private ArrayList<Document> documents = new ArrayList<>();

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		
		assert(documents != null);
		
		this.documents = documents;
	}

	@Override
	public void add(ArrayList<Document> model) {
		

		for(Document document : model) {
			
			DocumentDto documentDto = new DocumentDto();
			documentDto.add(document);
			
			this.documents.add(document);
			
		}
		
	}
	
	

}
