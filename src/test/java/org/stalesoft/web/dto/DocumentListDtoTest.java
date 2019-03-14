package org.stalesoft.web.dto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.stalesoft.model.Document;

@RunWith(MockitoJUnitRunner.class)
public class DocumentListDtoTest {
	
	@InjectMocks
	private DocumentListDto documentListDto;

	@Test
	public void testAddEmpty() {
		
		ArrayList<Document> emptyList = DocumentArrayListBuilder.create().empty().build();
		
		documentListDto.add(emptyList);
		assertEquals(documentListDto.getDocuments().size(), emptyList.size());
		
	}


	@Test
	public void testAdd() {
		
		ArrayList<Document> fullList = DocumentArrayListBuilder.create().add("doc1").build();
		
		documentListDto.add(fullList);
		assertEquals(documentListDto.getDocuments().size(), fullList.size());
		assertEquals(documentListDto.getDocuments().get(0).getTitle(),"doc1");
		
	}
	
	
	@Test
	public void testAddMultiple() {
		
		ArrayList<Document> fullList = DocumentArrayListBuilder.create().add("doc1").add("doc2").build();
		
		documentListDto.add(fullList);
		assertEquals(documentListDto.getDocuments().size(), fullList.size());
		
	}
	
}


class DocumentArrayListBuilder {
	
	ArrayList<Document> documentArrayList = null;
	
	public static DocumentArrayListBuilder create() {
		return new DocumentArrayListBuilder();
	}
	
	public DocumentArrayListBuilder empty() {
		documentArrayList = new ArrayList<>();
		return this;
	}
	
	
	
	public DocumentArrayListBuilder add(String documentName) {
		if (documentArrayList == null) {
			documentArrayList = new ArrayList<>();
		}
		
		Document document = new Document();
		document.setName(documentName);
		documentArrayList.add(document);
		
		return this;
	}
	
	public ArrayList<Document> build() {
		return documentArrayList;
	}
	
}