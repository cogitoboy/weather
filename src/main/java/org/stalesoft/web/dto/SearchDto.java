package org.stalesoft.web.dto;



public class SearchDto {
	
	
	private String query;
	private String fullContext;

	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return this.query;
	}

	public String getFullContext() {
		return fullContext;
	}

	public void setFullContext(String fullContext) {
		this.fullContext = fullContext;
	}
	
	

}
