package br.com.boavista.challenge.api.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.boavista.challenge.api.validation.ValidationGroups;

public class UsuarioInput {

	private Long id;
	
	@NotBlank
	private String nomeUsuario;
	
	@NotBlank
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtNascimento;
	
	private Long telefone;
	
	@NotNull
	private String senha;
	
	@Email
	private String email;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = ValidationGroups.Endereco.class)
	private EnderecoModel endereco;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = ValidationGroups.Perfil.class)
	private PerfilModel perfil;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
}