package br.com.boavista.challenge.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.boavista.challenge.api.validation.ValidationGroups;

public class EnderecoModel {

	private Long id;
	
	@NotBlank(groups = ValidationGroups.Endereco.class)
	private String logradouro;

	private Integer numero;

	private String complemento;

	@NotBlank(groups = ValidationGroups.Endereco.class)
	private String cep;

	@NotBlank(groups = ValidationGroups.Endereco.class)
	private String cidade;

	@Size(min = 2, max = 2, groups = ValidationGroups.Endereco.class)
	private String uf;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
