package org.stalesoft.data;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public interface DocumentDao {
	
	public static final String FOLDER_SEPARATOR = "/";
	public static final String ROOT_FOLDER = "/";

	public void saveDocument(String folder, Document document);
	
	public Document getDocumentByUuid(String uuid);
	public Document getDocumentByName(String documentName);
	public ArrayList<Document> getDocumentsByFolder(String folder);
	public ArrayList<Document> getDocumentVersionsById(String id);
	public ArrayList<Document> queryDocuments(String documentNameQuery);
}
	
