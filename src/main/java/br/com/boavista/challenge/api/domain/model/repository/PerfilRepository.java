package br.com.boavista.challenge.api.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boavista.challenge.api.domain.model.Perfil;
import br.com.boavista.challenge.api.domain.model.TipoPerfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	public Perfil findByTipoPerfil(TipoPerfil tipo);
}
