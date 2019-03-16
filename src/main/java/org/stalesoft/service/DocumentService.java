package org.stalesoft.service;

import java.util.ArrayList;

import org.stalesoft.model.Document;

public interface DocumentService {

	
	public String addDocument(String repository, String category, Document document);
	public String updateDocument(String repository, String category, Document document);
	public String mergeDocument(String reposiotry, String category, Document document);
	
	public String deleteDocument(String id);
	public String deleteDocumentVersion(String id, String version);
	
	
	
	public Document getDocument(String id);
	public ArrayList<Document> getDocuments(String repository);
	public ArrayList<Document> getDocuments(String repository, String category);
	
	
	public ArrayList<Document> findDocuments(String query);
	public ArrayList<Document> findDocuments(String repository, String query);
	public ArrayList<Document> findDocuments(String repository, String category, String query);
	
	
}
