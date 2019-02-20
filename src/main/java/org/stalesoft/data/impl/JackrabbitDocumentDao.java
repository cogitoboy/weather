package org.stalesoft.data.impl;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stalesoft.data.DocumentDao;
import org.stalesoft.model.Document;
import org.stalesoft.web.controller.LoggingAccessDeniedHandler;

@Repository("documentDao")
public class JackrabbitDocumentDao implements DocumentDao {
	
	private static Logger log = LoggerFactory.getLogger(JackrabbitDocumentDao.class);

	@Override
	public void saveDocument(Document document) {
		//
		
	}

}
