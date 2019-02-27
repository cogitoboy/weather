package org.stalesoft.data;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public interface DocumentDao {

	public void saveDocument(Document document);
	public Document getDocument(String id);
	public ArrayList<Document> queryDocuments(String query);
}
