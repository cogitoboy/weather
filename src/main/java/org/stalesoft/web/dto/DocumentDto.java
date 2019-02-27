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

		DocumentDto documentDto = new DocumentDto();
		documentDto.setTitle(model.getTitle());

		// TODO: needs to be an enum
		if (model.getType() != null) {
			switch (model.getType()) {

			case 1:
				documentDto.setTitle("Title One");
			default:
				documentDto.setTitle("Unknown");

			}
		}
	}
	
	
	
}
