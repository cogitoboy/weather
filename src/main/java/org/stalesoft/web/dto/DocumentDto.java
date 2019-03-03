package org.stalesoft.web.dto;

import org.stalesoft.model.Document;

public class DocumentDto implements BaseDto<Document>{
	
	
	private String type;
	private String documentTitle;
	
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	public String getTitle() {
		return documentTitle;
	}
	
	public void setTitle(String title) {
		this.documentTitle = title;
	}

	@Override
	public void add(Document document) {

		setTitle(document.getName());
		setType(document.getMimeType());
		
	}
	
	
	
}
