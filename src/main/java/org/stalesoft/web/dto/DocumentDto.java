package org.stalesoft.web.dto;

import java.util.Date;

import org.stalesoft.model.Document;

public class DocumentDto implements BaseDto<Document>{
	
	
	private String type;
	private String documentTitle;
	private String uuid;
	private String version;
	private String consumerId;
	private String consumerName;
	private String documentId;
	private String description;
	private Date archiveDate;
	private Date documentDate;
	private String category;
	
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
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public void add(Document document) {

		setTitle(document.getName());
		setType(document.getMimeType());
		setUuid(document.getUuid());
		setVersion(document.getVersion());
		setConsumerId(document.getConsumerId());
		setDocumentId(document.getDocumentId());
		setDescription(document.getDescription());
		setArchiveDate(document.getArchiveDate());
		setDocumentDate(document.getDocumentDate());
		setConsumerName(document.getConsumerName());
		setCategory(document.getCategory());
	}
	
	
	
}
