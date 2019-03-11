package org.stalesoft.web.dto;

import java.util.UUID;

import org.stalesoft.model.Document;

public class DocumentDto implements BaseDto<Document>{
	
	
	private String type;
	private String documentTitle;
	private String uuid;
	
	
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
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	

	@Override
	public void add(Document document) {

		setTitle(document.getName());
		setType(document.getMimeType());
		setUuid(document.getUuid());
		
	}
	
	
	
}
