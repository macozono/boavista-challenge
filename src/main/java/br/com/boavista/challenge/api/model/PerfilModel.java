package br.com.boavista.challenge.api.model;

import br.com.boavista.challenge.api.domain.model.TipoPerfil;

public class PerfilModel {

	private Long id;
	private TipoPerfil tipoPerfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPerfil getTipoPerfil() {
		return tipoPerfil;
	}

	public void setTipoPerfil(TipoPerfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}
}
