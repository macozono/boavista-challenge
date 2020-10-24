package br.com.boavista.challenge.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class JwtInput implements Serializable {

	private static final long serialVersionUID = -5213178464714978821L;
	
	@NotBlank
	private String usuario;
	@NotBlank
	private String senha;
	
	public JwtInput() {
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
