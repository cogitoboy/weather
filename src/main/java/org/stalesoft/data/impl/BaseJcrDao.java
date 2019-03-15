package org.stalesoft.data.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.nodetype.NodeType;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.jackrabbit.commons.cnd.CndImporter;
import org.apache.jackrabbit.commons.cnd.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.stalesoft.model.Document;

public class BaseJcrDao {

	public static final Long LOGIN_ERROR = "LOGIN_ERROR".hashCode() + 0L;
	public static final Long REPOSITORY_ERROR = "REPOSITORY_ERROR".hashCode() + 0L;

	private static Logger log = LoggerFactory.getLogger(BaseJcrDao.class);

	@Autowired
	private Repository repository;

	protected Session session;

	protected Session openSession() {

		// TODO Real session authentication
		if (session == null || !session.isLive()) {

			try {

				session = repository.login(new SimpleCredentials("admin", "superSecret!".toCharArray()));
				URL x = getClass().getClassLoader().getResource("static/cnd/stalesoft.cnd");
				File file = new File(x.getFile());
				FileReader fileReader = new FileReader(file);

				NodeType[] nodeTypes = CndImporter.registerNodeTypes(fileReader, session, true);

				for (NodeType nt : nodeTypes) {
					log.debug("Registered node type : " + nt.getName());
				}

			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return session;

	}
	
	protected Document executeDocumentQuery(String queryString) {
		
		
		Document document = null;
		
		ArrayList<Document> documents = executeDocumentSetQuery(queryString);
		
		if (documents.size() > 1) {
			//TODO throw DAOException
			throw new RuntimeException("Excpeted on document, found " + documents.size());
		}
		
		if (documents.size() == 1) {
			document = documents.get(0);
		}
		
		return document;
		
	}

	protected ArrayList<Document> executeDocumentSetQuery(String queryString) {
		
		log.debug("executing query: {}", queryString);

		ArrayList<Document> documents = new ArrayList<>();

		try {
			openSession();

			QueryManager queryManager = session.getWorkspace().getQueryManager();

			Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
			QueryResult result = query.execute();

			NodeIterator nodeIter = result.getNodes();

			while (nodeIter.hasNext()) {

				Node node = nodeIter.nextNode();
				
				Document document = new Document();
				
				document.setCategory(node.getParent().getName());
				document.setRepository(node.getParent().getParent().getName());
				
				document.setName(getNodeProperty(node, "doc:name"));
				document.setMimeType(getNodeProperty(node, "jcr:mimeType"));
				document.setUuid(getNodeProperty(node, "doc:uuid"));
				document.setArchiveDate(getNodePropertyDate(node, "doc:archivedate"));
				document.setDocumentDate(getNodePropertyDate(node, "doc:documentdate"));
				document.setConsumerId(getNodeProperty(node, "doc:consumerid"));
				document.setConsumerName(getNodeProperty(node, "doc:consumername"));
				document.setDescription(getNodeProperty(node, "doc:description"));
				document.setDocumentId(getNodeProperty(node, "doc:documentid"));
				document.setInputStream(JcrUtils.readFile(node));
				document.setVersion("1.1");//TODO: Get the latest version
				
				documents.add(document);

			}

		} catch (RepositoryException e) {
			// TODO Throw custom exception
			e.printStackTrace();
		} finally {

			closeSession();
		}

		return documents;

	}

	protected void closeSession() {

		session.logout();

	}
	
	protected Node getDocumentNode(String folder, String documentName) throws RepositoryException {
		
		
		Node folderNode = getFolder(folder);

		Node documentNode = null;
		
		if (folderNode.hasNode(documentName)) {
			
			documentNode = folderNode.getNode(documentName);
			
		} else {
		
			documentNode = folderNode.addNode(documentName, "nt:file");
			Node contentNode = documentNode.addNode("jcr:content", "nt:resource");
			contentNode.addMixin("doc:stalesoft");
		}
		
		return documentNode;
		
	}
	


	protected Node getFolder(String xpath) throws RepositoryException {

		Node rootNode = session.getRootNode();

		// TODO externalize the jcr names
		Node folderNode = JcrUtils.getOrCreateByPath(rootNode, xpath, false, "nt:folder", "nt:folder", true);

		return folderNode;

	}

	protected Date getNodePropertyDate(Node node, String propertyName) throws RepositoryException {
		
		Date date = null;
		
		String dateString = getNodeProperty(node, propertyName);
		
		if (dateString != null && dateString.chars().allMatch(Character::isDigit)) {
			
			date = new Date(new Long(dateString));
		}
		
		return date;
		
	}
	protected String getNodeProperty(Node node, String propertyName) throws RepositoryException {

		String value = null;

		Property property = null;
		try {

			property = node.getProperty(propertyName);

		} catch (PathNotFoundException e) {
			// Is okay if not found
			log.debug(e.getMessage(), e);

		}

		if (property != null) {
			value = property.getValue().getString();
		}

		return value;
	}

}
