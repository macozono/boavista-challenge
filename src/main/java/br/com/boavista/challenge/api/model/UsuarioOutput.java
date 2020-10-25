package br.com.boavista.challenge.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioOutput {

	private Long id;
	
	private String nomeUsuario;
	
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtNascimento;
	
	private Long telefone;
	
	private String email;
	
	private EnderecoModel endereco = new EnderecoModel();
	
	private PerfilModel perfil = new PerfilModel();
	
	private JwtModel jwt = new JwtModel();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public PerfilModel getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilModel perfil) {
		this.perfil = perfil;
	}
	
	public JwtModel getJwt() {
		return jwt;
	}

	public void setJwt(JwtModel token) {
		this.jwt = token;
	}
}