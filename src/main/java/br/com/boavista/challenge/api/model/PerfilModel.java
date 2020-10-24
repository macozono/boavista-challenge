package br.com.boavista.challenge.api.model;

import javax.validation.constraints.NotNull;

import br.com.boavista.challenge.api.domain.model.TipoPerfil;
import br.com.boavista.challenge.api.validation.ValidationGroups;

public class PerfilModel {

	@NotNull(groups = ValidationGroups.Perfil.class)
	private Long id;
	@NotNull(groups = ValidationGroups.Perfil.class)
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
