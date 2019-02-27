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

	private Repository repository;
	private Session session;
	
	@Autowired
	private DocumentDao documentDao;

	@Autowired
	public JcrDocumentService(RepositoryImpl jcrRespository) throws Exception {
		this.repository = jcrRespository;
		
		
		session = repository.login(new SimpleCredentials("admin", "superSecret!".toCharArray()));
		try {
			String user = session.getUserID();
			String name = repository.getDescriptor(Repository.REP_NAME_DESC);
			System.out.println("Logged in as " + user + " to a " + name + " repository.");
		} finally {
			session.logout();
		}
	}

	@Override
	public Document getDocument(String id) {
		
		Document document = documentDao.getDocument(id);
		return null;
	}

	@Override
	public ArrayList<Document> findDocuments(String query) {
		
		ArrayList<Document> documents = documentDao.queryDocuments(query);
		
		return documents;
		
	}
}
