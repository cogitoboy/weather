package org.stalesoft.data.impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.jackrabbit.core.RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stalesoft.data.DocumentDao;
import org.stalesoft.model.Document;

//TODO Make a database repository
@Repository("documentDao")
public class JcrDocumentDao implements DocumentDao {
	
	private static Logger log = LoggerFactory.getLogger(JcrDocumentDao.class);


	private javax.jcr.Repository repository;
	private Session session;
	
	@Autowired
	public JcrDocumentDao(RepositoryImpl jcrRespository) throws Exception {
		
		this.repository = jcrRespository;
	}
	
	//TODO Real session authentication
	protected void openSession() {
		
		try {
			
			session = repository.login(new SimpleCredentials("admin", "superSecret!".toCharArray()));
			
		} catch (RepositoryException e) {
			// TODO Throw a DTO exception
			e.printStackTrace();
		}
		
	}

	protected void closeSession() {
	    
		session.logout();
	
	}
	
	private Node getFolder(String xpath) throws RepositoryException {
		
		Node rootNode = session.getRootNode();
		
		//TODO externalize the jcr names
		Node folderNode = JcrUtils.getOrCreateByPath(rootNode, xpath, false, "nt:folder", "nt:folder", true);
		
		return folderNode;
		
	}
	
	@Override
	public void saveDocument(Document document) {
		
		openSession();//TODO AOP? Some other approach  to opening and closing session. Transactions etc.  Some cleverness needed.
		
		
		String path = document.getPath();
		
		assert(path != null);
		assert("".equals(path));
		
		try {
			Node folder = getFolder(path);
			
			//TODO: Add versioning https://wiki.apache.org/jackrabbit/ExamplesPage
			Node documentNode = folder.addNode(document.getName(), "nt:file");
			Node contentNode = documentNode.addNode("jcr:content","nt:resource");
			
			Binary binary = session.getValueFactory().createBinary(document.getInputStream());
			
			contentNode.setProperty("jcr:data", binary);
			contentNode.setProperty("jcr:mimeType", document.getMimeType());
			contentNode.setProperty("jcr:lastModified", Calendar.getInstance());
			
			session.save();
			
		} catch (RepositoryException e) {
			//TODO need to throw a dao exception
			e.printStackTrace();
		} finally {
			closeSession();
		}
		
	}


	@Override
	public Document getDocument(String id) {
		Document document = new Document();
		
		
		return document;
	}


	@Override
	public ArrayList<Document> queryDocuments(String queryString) {
		
		try {
		
			openSession();
			
			javax.jcr.query.QueryManager queryManager = session.getWorkspace().getQueryManager();

			
            javax.jcr.query.Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
			
			javax.jcr.query.QueryResult result = query.execute();
			
			
			javax.jcr.NodeIterator nodeIter = result.getNodes();

			while ( nodeIter.hasNext() ) {

			    javax.jcr.Node node = nodeIter.nextNode();

			    Document document = new Document();
			    document.setName(node.getName());
			    
			}
			
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			closeSession();
		}
		
		
		ArrayList<Document> documents = new ArrayList<>();
		
		Document document_1 = new Document();
		document_1.setName("My Title");
		document_1.setMimeType("jpg");
		
		documents.add(document_1);
		
		return documents;
		
	}


}
