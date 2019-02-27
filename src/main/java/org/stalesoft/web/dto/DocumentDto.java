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
	public void add(Document model) {

		setTitle(model.getTitle());

		// TODO: needs to be an enum 
		if (model.getType() != null) {
			switch (model.getType()) {

			case 1:
				setType("Title One");
			default:
				setType("Unknown");

			}
		}
	}
	
	
	
}
