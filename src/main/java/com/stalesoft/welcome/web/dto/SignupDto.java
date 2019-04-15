package com.stalesoft.welcome.web.dto;

import java.io.Serializable;

public class SignupDto extends ValidatedDto implements Serializable {
	
	private static final long serialVersionUID = -4884833543114202680L;
	
	private String email;
	private String password;
	private Boolean error;

	public String getEmail() {
		return email;
	}

	public SignupDto setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public SignupDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

}
