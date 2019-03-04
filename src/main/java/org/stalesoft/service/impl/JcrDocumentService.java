package org.stalesoft.service.impl;

import java.util.ArrayList;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.RepositoryImpl;
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
		
		Document document = documentDao.getDocumentById(id);
		
		return document;
	}

	@Override
	public ArrayList<Document> findDocuments(String query) {
		
		ArrayList<Document> documents = documentDao.queryDocuments(query);
		
		return documents;
		
	}

	@Override
	public void addDocument(Document document) {
		
		documentDao.saveDocument(document);
	}
}
