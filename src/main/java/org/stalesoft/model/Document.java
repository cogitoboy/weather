package org.stalesoft.model;

import java.io.InputStream;

public class Document {

	public static final String ROOT = "/";
	
	public Document() {
		
	}

	public Document(String folder) {
		assert (folder != null);
		assert ("".equalsIgnoreCase(folder));
		
		this.folder = folder;
		
	}
	
	private String folder = Document.ROOT;
	private String name;
	private String mimeType;
	private String uuid;
	
	private InputStream inputStream;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String path) {
		this.folder = path;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	
	
}
