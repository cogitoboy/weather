package org.stalesoft.model;

import java.io.FileInputStream;

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
	private FileInputStream fileInputStream;
	
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

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	
	
	
	
}
