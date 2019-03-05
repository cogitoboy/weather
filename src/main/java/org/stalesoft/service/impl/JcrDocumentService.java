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
	public Document getDocument(String id) {
		
		Document document = documentDao.getDocumentByUuid(id);
		
		return document;
	}

	@Override
	public ArrayList<Document> findDocuments(String query) {
		
		ArrayList<Document> documents = documentDao.queryDocuments(query);
		
		return documents;
		
	}

	@Override
	public String addDocument(Document document) {
		
		assert(document.getUuid() == null);
		
		UUID uuid = UUID.randomUUID();
		document.setUuid(uuid.toString());//UUID is each specific upload.
		
		documentDao.saveDocument(document);
		
		return uuid.toString();
	}
}
