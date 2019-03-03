package org.stalesoft.data;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public interface DocumentDao {

	public void saveDocument(Document document);
	public Document getDocumentById(String id);
	public Document getDocumentByName(String documentName);
	public ArrayList<Document> getDocumentsByFolder(String folderPath);
	public ArrayList<Document> getDocumentVersionsById(String id);
	public ArrayList<Document> queryDocumentsByName(String documentNameQuery);
	public ArrayList<Document> queryDocumentsByNameandType(String documentNameQuery, String mimeType);
}
