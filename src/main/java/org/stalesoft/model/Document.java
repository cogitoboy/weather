package org.stalesoft.model;

import java.io.FileInputStream;
import java.io.InputStream;

public class Document {

	public Document() {
		this.folder = new Folder();
		this.folder.setFolderName(Folder.ROOT);
	}

	public Document(Folder folder) {
		
		assert(folder != null);
		
		this.folder = folder;
	}
	
	private Folder folder;
	private String name;
	private Integer mimeType;
	private InputStream inputStream;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Integer getMimeType() {
		return mimeType;
	}
	
	public void setMimeType(Integer mimeType) {
		this.mimeType = mimeType;
	}
	
	public Folder getFolder() {
		return folder;
	}
	
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	
	
}
