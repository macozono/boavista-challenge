package br.com.boavista.challenge.api.model;

import java.io.Serializable;

public class JwtModel implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String token;
	
	public JwtModel() {
		
	}
	
	public JwtModel(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
