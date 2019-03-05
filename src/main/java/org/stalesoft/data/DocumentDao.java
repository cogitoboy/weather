package org.stalesoft.data;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public interface DocumentDao {

	public void saveDocument(Document document);
	public Document getDocumentByUuid(String uuid);
	public Document getDocumentByName(String documentName);
	public ArrayList<Document> getDocumentsByFolder(String folderPath);
	public ArrayList<Document> getDocumentVersionsById(String id);
	public ArrayList<Document> queryDocuments(String documentNameQuery);
}
	
