package org.stalesoft.service;

import java.util.ArrayList;

import org.apache.jackrabbit.core.RepositoryImpl;
import org.stalesoft.model.Document;

public interface DocumentService {

	
	public String addDocument(Document document);
	public Document getDocument(String id);
	public ArrayList<Document> findDocuments(String query);
	
	
}
