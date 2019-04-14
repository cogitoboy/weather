package com.stalesoft.welcome.web.dto;

import java.io.Serializable;

public class SignupDto implements Serializable {
	
	private static final long serialVersionUID = -4884833543114202680L;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
