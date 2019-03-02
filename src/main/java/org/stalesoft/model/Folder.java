package org.stalesoft.model;

import java.util.ArrayList;

public class Folder {
	
	public static final String ROOT = "/";
	
	private Folder parentFolder;
	private ArrayList<Folder> subfolders;
	private String folderName;
	
	
	public Folder getParentFolder() {
		return parentFolder;
	}


	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}


	public ArrayList<Folder> getSubfolders() {
		return subfolders;
	}


	public void setSubfolders(ArrayList<Folder> subfolders) {
		this.subfolders = subfolders;
	}


	public String getFolderName() {
		return folderName;
	}


	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}


	public String getXpath() {
		return "";
	}
	
	

}
