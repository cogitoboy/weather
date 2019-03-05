package org.stalesoft.data.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.nodetype.NodeType;
import javax.jcr.query.Query;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.jackrabbit.commons.cnd.CndImporter;
import org.apache.jackrabbit.commons.cnd.ParseException;
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
			URL x = getClass().getClassLoader().getResource("static/cnd/stalesoft.cnd");
			File file = new File(x.getFile());
			FileReader fileReader = new FileReader(file);
			NodeType[] nodeTypes = CndImporter.registerNodeTypes(fileReader, session, true);
			for (NodeType nt : nodeTypes) {
                System.out.println("Registered: " + nt.getName());
			}
			
		} catch (RepositoryException e) {
			// TODO Throw a DTO exception
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		assert(document.getInputStream() != null);
		assert(document.getMimeType() != null);
		assert(document.getName() != null);
		assert(document.getUuid() != null);
		
		String path = document.getPath();
		
		assert(path != null);
		assert("".equals(path));
		
		try {
			Node folder = getFolder(path);
			
			//TODO: need to add a UUID
			//TODO: Add versioning https://wiki.apache.org/jackrabbit/ExamplesPage
			Node documentNode = folder.addNode(document.getName(), "nt:file");
			Node contentNode = documentNode.addNode("jcr:content","nt:resource");
			
			Binary binary = session.getValueFactory().createBinary(document.getInputStream());
			
			contentNode.addMixin("doc:stalesoft");
			contentNode.setProperty("doc:name", document.getName().toLowerCase());
			contentNode.setProperty("doc:uuid", document.getUuid());
			contentNode.setProperty("jcr:data", binary);
			contentNode.setProperty("jcr:mimeType", document.getMimeType().toLowerCase());
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
	public Document getDocumentById(String id) {
		Document document = new Document();
		
		
		return document;
	}


	//Query: http://drfits.com/jcr-sql2-query-with-examples/
	@Override
	public ArrayList<Document> queryDocuments(String queryString) {
	
		ArrayList<Document> documents = new ArrayList<>();
		
		
		try {
		
			openSession();
			
			javax.jcr.query.QueryManager queryManager = session.getWorkspace().getQueryManager();

			String sql1 = "SELECT p.* FROM [nt:resource] AS p WHERE  "
					+ "p.[jcr:mimeType] like '%" + queryString.toLowerCase() + "%' "
					+ "OR "
					+ "p.[doc:name] like '%" + queryString.toLowerCase() + "%' "
					+ "OR "
					+ "p.[doc:uuid] like '%" + queryString.toLowerCase() + "%' "
					;
			
			
					
			
            javax.jcr.query.Query query = queryManager.createQuery(sql1, Query.JCR_SQL2);
			
			javax.jcr.query.QueryResult result = query.execute();
			
			
			javax.jcr.NodeIterator nodeIter = result.getNodes();
			
			log.debug("Query {} found {} documents", sql1, nodeIter.getSize());

			while ( nodeIter.hasNext() ) {

			    javax.jcr.Node node = nodeIter.nextNode();
			    
			    log.debug("Found document - name: %s, mimeType: %s");
			    
			    Document document = new Document();
			    document.setName(getNodeProperty(node,"doc:name"));
			    document.setMimeType(getNodeProperty(node, "jcr:mimeType"));
			    document.setPath(node.getParent().getPath());
			    document.setUuid(getNodeProperty(node,"doc:uuid"));
		
			    documents.add(document);
			   
			}
			
		} catch (RepositoryException e) {
			// TODO Throw DTO exception, handle gracefully
			e.printStackTrace();
		} finally {
			
			closeSession();
		}
		
		return documents;
		
	}

	
	private String getNodeProperty(Node node, String propertyName) throws RepositoryException  {
		
		String value = null;
		
		Property property = null;
		try {
			
			property = node.getProperty(propertyName);
			
		} catch (PathNotFoundException e) {
			//Is okay if not found
			log.debug(e.getMessage(), e);
			
		}
		
		if (property != null) {
			value = property.getValue().getString();
		}
		
		return value;
	}
	@Override
	public Document getDocumentByName(String documentName) {

		Document document = null;
		
		try {
		
			openSession();
			
			javax.jcr.query.QueryManager queryManager = session.getWorkspace().getQueryManager();

			String sql1 = "SELECT p.* FROM [nt:file] AS p WHERE NAME(p) LIKE '" + documentName + "'";
			
            javax.jcr.query.Query query = queryManager.createQuery(sql1, Query.JCR_SQL2);
			
			javax.jcr.query.QueryResult result = query.execute();
			
			
			javax.jcr.NodeIterator nodeIter = result.getNodes();

			
			
			if ( nodeIter.hasNext() ) {

			    javax.jcr.Node node = nodeIter.nextNode();
			    
			    log.debug("Found document - name: %s, mimeType: %s");
			    
			    document = new Document();
			    document.setName(node.getName());
			    document.setMimeType(node.getProperty("jcr:content/jcr:mimeType").getValue().getString());
			    document.setPath(node.getParent().getPath());
			    //TODO Display_Download_Binary_File_data https://stackoverflow.com/questions/33087470/jackrabbit-file-storage, https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc
			}
			
		} catch (RepositoryException e) {
			// TODO Throw DTO exception
			e.printStackTrace();
		} finally {
			
			closeSession();
		}
		
		return document;
	}

	@Override
	public ArrayList<Document> getDocumentsByFolder(String folderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Document> getDocumentVersionsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
