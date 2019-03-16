package org.stalesoft.web.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.stalesoft.model.Document;

@RunWith(MockitoJUnitRunner.class)
public class DocumentDtoTest {
	
	@InjectMocks
	private DocumentDto documentDto;
	
	@Mock
	private InputStream mockFileInputStream;
	
	@Test
	public void testAddEmpty() {
		
		Document document = DocumentBuilder.create().empty().build();
		
		documentDto.add(document);
		
		assertNull(document.getName());
		assertNotNull(document.getArchiveDate());
		assertNull(document.getCategory());
		assertNull(document.getUuid());
		assertNull(document.getVersion());
		assertNotNull(document.getDocumentDate());
		assertNull(document.getInputStream());
	}
	
	@Test
	public void testAdd() {
		
		Document document = DocumentBuilder.create().fullDocument().build();
		
		documentDto.add(document);
		
		assertEquals(documentDto.getArchiveDate(), document.getArchiveDate());
		assertEquals(documentDto.getCategory(), document.getCategory());
		assertEquals(documentDto.getConsumerId(), document.getConsumerId());
		assertEquals(documentDto.getConsumerName(), document.getConsumerName());
		assertEquals(documentDto.getDescription(), document.getDescription());
		assertEquals(documentDto.getDocumentDate(), document.getDocumentDate());
		assertEquals(documentDto.getDocumentId(), document.getDocumentId());
		assertEquals(documentDto.getTitle(), document.getName());
		assertEquals(documentDto.getType(), document.getMimeType());
		assertEquals(documentDto.getUuid(), document.getUuid());
		assertEquals(documentDto.getVersion(), document.getVersion());
		
	}
}


class DocumentBuilder {
	
	public static final Date DEFAULT_ARCHIVE_DATE = new Date();
	public static final String DEFAULT_CATEGORY = "auth_letter";
	public static final String DEFAULT_REPOSITORY = "opas";
	public static final String DEFAULT_CONSUMER_ID = "123123";
	public static final String DEFAULT_CONSUMER_NAME = "Smith, Markus";
	public static final String DEFAULT_DESCRIPTION = "Last file sent";
	public static final Date DEFAULT_DOCUMENT_DATE = new Date();
	public static final String DEFAULT_DOCUMENT_ID = "3333221";
	public static final String DEFAULT_MIME_TYPE = "text/plain";
	public static final String DEFAULT_NAME = "Name";
	public static final String DEFAULT_UUID = "99c35b20-ff8f-4169-96c5-164ef8098b91";
	public static final String DEFAULT_VERSION = "1.1";
	
	Document document = null;
	
	public static DocumentBuilder create() {
		return new DocumentBuilder();
	}
	
	public DocumentBuilder empty() {
		document = new Document();
		return this;
	}
		
	public DocumentBuilder fullDocument() {
		document = new Document();
		document.setArchiveDate(DEFAULT_ARCHIVE_DATE);
		document.setCategory(DEFAULT_CATEGORY);
		document.setRepository(DEFAULT_REPOSITORY);
		document.setConsumerId(DEFAULT_CONSUMER_ID);
		document.setConsumerName(DEFAULT_CONSUMER_NAME);
		document.setDescription(DEFAULT_DESCRIPTION);
		document.setDocumentDate(DEFAULT_DOCUMENT_DATE);
		document.setDocumentId(DEFAULT_DOCUMENT_ID);
		document.setMimeType(DEFAULT_MIME_TYPE);
		document.setName(DEFAULT_NAME);
		document.setUuid(DEFAULT_UUID);
		document.setVersion(DEFAULT_VERSION);
		
		return this;
	}
	
	public DocumentBuilder inputStream(InputStream inputStream) {
		if (document == null) {
			document = new Document();
		}
		
		document.setInputStream(inputStream);
		
		return this;
	}
	
	public Document build() {
		return document;
	}
	
}
