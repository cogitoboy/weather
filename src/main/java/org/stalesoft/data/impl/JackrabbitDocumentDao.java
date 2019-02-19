package org.stalesoft.data.impl;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stalesoft.data.DocumentDao;
import org.stalesoft.model.Document;

@Repository("documentDao")
public class JackrabbitDocumentDao implements DocumentDao {


	@Override
	public void saveDocument(Document document) {
		//
		
	}

}
