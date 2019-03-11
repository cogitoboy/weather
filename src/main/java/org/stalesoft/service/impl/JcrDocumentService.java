package org.stalesoft.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stalesoft.data.DocumentDao;
import org.stalesoft.model.Document;
import org.stalesoft.service.DocumentService;

@Service
public class JcrDocumentService implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	

	@Override
	public Document getDocument(String uuid) {
		
		Document document = documentDao.getDocumentByUuid(uuid);
		
		return document;
	}

	


	@Override
	public String addDocument(Document document) {
		
		assert(document.getUuid() == null);
		
		UUID uuid = UUID.randomUUID();
		document.setUuid(uuid.toString());//UUID is each specific upload.
		
		documentDao.saveDocument(document);
		
		return uuid.toString();
	}

	/**
	 * Get all documents for a specific folder. Must be a leaf folder.
	 */
	@Override
	public ArrayList<Document> getDocuments(String folder) {
		
		//TODO: Need implemented
		return new ArrayList<>();

	}

	/**
	 * Find document searching all folders
	 */
	@Override
	public ArrayList<Document> findDocuments(String query) {
		
		ArrayList<Document> documents = documentDao.queryDocuments(query);
		
		return documents;
		
	}
	
	/**
	 * Find document searching specific folder. Doesn't have to be a leaf folder.
	 */
	@Override
	public ArrayList<Document> findDocuments(String folder, String query) {
		// TODO Need to be implemented
		return new ArrayList<>();
	}
}
