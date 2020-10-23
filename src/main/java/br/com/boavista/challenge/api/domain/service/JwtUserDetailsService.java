package br.com.boavista.challenge.api.domain.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.boavista.challenge.api.domain.model.Usuario;
import br.com.boavista.challenge.api.domain.model.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Usuario usuario = repository.findByNomeUsuario(nomeUsuario);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		return new User(usuario.getNomeUsuario(), usuario.getSenha(), new ArrayList<>());
	}
}