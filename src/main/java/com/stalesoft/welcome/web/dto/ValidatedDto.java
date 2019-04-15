package com.stalesoft.welcome.web.dto;

public class ValidatedDto {

	
	Iterable<String> errorMessages;
	
	Boolean error = Boolean.FALSE;

	public Iterable<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Iterable<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
}
