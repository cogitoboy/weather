package org.stalesoft.data.impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.stalesoft.data.DocumentDao;
import org.stalesoft.model.Document;

//TODO Make a database repository
@Repository("documentDao")
public class JcrDocumentDao extends BaseJcrDao implements DocumentDao {

	private static Logger log = LoggerFactory.getLogger(JcrDocumentDao.class);

	protected static String FULL_QUERY_SQL =  "SELECT p.* FROM [nt:resource] AS p "
							+ "WHERE p.[jcr:mimeType] like '%%%1$s%%'"
							+ " OR p.[doc:name] like '%%%1$s%%'"
							+ " OR p.[doc:consumerid] like '%%%1$s%%'"
							+ " OR p.[doc:category] like '%%%1$s%%'"
							+ " OR p.[doc:documentid] like '%%%1$s%%'"
							+ " OR p.[doc:description] like '%%%1$s%%'"
							+ " OR p.[doc:consumername] like '%%%1$s%%'"
							+ " OR p.[doc:uuid] like '%%%1$s%%' ";
	protected static String GET_BY_UUID_SQL = "SELECT p.* FROM [nt:resource] AS p WHERE p.[doc:uuid] LIKE '%%%1$s%%' ";
	protected static String GET_BY_NAME_SQL = "SELECT p.* FROM [nt:file] AS p WHERE NAME(p) LIKE '%%%1$s%%'";

	@Override
	public void saveDocument(String folder, Document document) {

		openSession();// TODO AOP? Some other approach to opening and closing session. Transactions
						// etc. Some cleverness needed.
		assert (document.getInputStream() != null);
		assert (document.getMimeType() != null);
		assert (document.getName() != null);
		assert (document.getUuid() != null);
		assert (folder != null);
		assert ("".equals(folder));

		try {

			Node documentNode = getDocumentNode(folder, document.getName());
			Node contentNode = (Node)documentNode.getNodes().next();
			
			Binary binary = session.getValueFactory().createBinary(document.getInputStream());
			
			contentNode.setProperty("doc:name", document.getName().toLowerCase());
			contentNode.setProperty("doc:uuid", document.getUuid());
			contentNode.setProperty("doc:consumerid", document.getConsumerId());
			contentNode.setProperty("doc:consumername", document.getConsumerName());
			contentNode.setProperty("doc:documentid", document.getDocumentId());
			contentNode.setProperty("doc:description", document.getDescription());
			contentNode.setProperty("doc:archivedate", document.getArchiveDate().getTime());//TODO: make the required attributes required e.g. constructor
			contentNode.setProperty("doc:documentdate", document.getDocumentDate().getTime());//TODO: make the required attributes required e.g. constructor
			contentNode.setProperty("jcr:data", binary);
			contentNode.setProperty("jcr:mimeType", document.getMimeType().toLowerCase());
			contentNode.setProperty("jcr:lastModified", Calendar.getInstance());
			
			session.save();
			log.debug("document saved");

		} catch (RepositoryException e) {
			// TODO need to throw a dao exception
			e.printStackTrace();
		} finally {
			closeSession();
		}

	}

	@Override
	public Document getDocumentByUuid(String uuid) {

		Document document = executeDocumentQuery(String.format(GET_BY_UUID_SQL, uuid));

		if (document == null) {
			// TODO throw dao exception
			throw new RuntimeException("Cannot get Document by uuid : " + uuid);
		}

		return document;
	}

	/**
	 * http://drfits.com/jcr-sql2-query-with-examples/
	 */
	@Override
	public ArrayList<Document> queryDocuments(String queryString) {

		ArrayList<Document> documents = new ArrayList<>();

		documents = executeDocumentSetQuery(String.format(FULL_QUERY_SQL, queryString));

		return documents;

	}

	@Override
	public Document getDocumentByName(String documentName) {
		throw new RuntimeException("Not yet Implemented");
	}

	@Override
	public ArrayList<Document> getDocumentsByFolder(String folderPath) {
		throw new RuntimeException("Not yet Implemented");
	}

	@Override
	public ArrayList<Document> getDocumentVersionsById(String id) {
		throw new RuntimeException("Not yet Implemented");
	}

}
