package org.stalesoft.data.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.stalesoft.data.DocumentDao;
import org.stalesoft.model.Document;

@Repository("documentDao")
public class JackrabbitDocumentDao implements DocumentDao {
	
	private static Logger log = LoggerFactory.getLogger(JackrabbitDocumentDao.class);

	
	@Override
	public void saveDocument(Document document) {
		//
		
	}


	@Override
	public Document getDocument(String id) {
		Document document = new Document();
		
		
		return document;
	}


	@Override
	public ArrayList<Document> queryDocuments(String query) {
		
		ArrayList<Document> documents = new ArrayList<>();
		
		Document document_1 = new Document();
		
		documents.add(document_1);
		
		return documents;
		
	}


}
