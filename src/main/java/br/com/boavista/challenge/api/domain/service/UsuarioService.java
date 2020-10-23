package br.com.boavista.challenge.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.boavista.challenge.api.domain.exception.NegocioBaseException;
import br.com.boavista.challenge.api.domain.model.Perfil;
import br.com.boavista.challenge.api.domain.model.TipoPerfil;
import br.com.boavista.challenge.api.domain.model.Usuario;
import br.com.boavista.challenge.api.domain.model.repository.PerfilRepository;
import br.com.boavista.challenge.api.domain.model.repository.UsuarioRepository;
import br.com.boavista.challenge.api.model.JwtModel;
import br.com.boavista.challenge.api.model.UsuarioCreateInput;
import br.com.boavista.challenge.api.model.UsuarioCreateOutput;
import br.com.boavista.challenge.api.model.UsuarioUpdateInput;
import br.com.boavista.challenge.api.model.UsuarioUpdateOutput;
import br.com.boavista.challenge.api.util.JwtTokenUtil;
import br.com.boavista.challenge.api.util.ModelMapperUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ModelMapperUtil mapper;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public List<Usuario> listar(String username) {
		Usuario usuario = repository.findByNomeUsuario(username);
		
		if (usuario.getPerfil().getTipoPerfil() != TipoPerfil.ADMIN) {
			throw new RuntimeException("");
		}
		
		return repository.findAll();
	}
	
	public UsuarioCreateOutput criar(UsuarioCreateInput input) {
		Usuario usuarioExistente = repository.findByNomeUsuario(input.getNomeUsuario());
		
		if (usuarioExistente != null) {
			throw new NegocioBaseException("Já existe um usuário cadastro com esse nome.");
		}

		Usuario usuario = mapper.toEntity(input, Usuario.class);
		
		String passwdHash = BCrypt.hashpw(input.getSenha(), BCrypt.gensalt());
		usuario.setSenha(passwdHash);
		
		Perfil perfil = perfilRepository.findByTipoPerfil(input.getPerfil());
		usuario.setPerfil(perfil);
		
		usuario = repository.save(usuario);
		
		UsuarioCreateOutput output = mapper.toModel(usuario, UsuarioCreateOutput.class);
		String token = jwtTokenUtil.generateToken(usuario.getNomeUsuario());
		
		JwtModel jwt = new JwtModel(token);
		output.setToken(jwt);
		
		return output;
	}
	
	public UsuarioUpdateOutput alterar(UsuarioUpdateInput input) {
		Usuario usuarioExistente = repository.findById(input.getId()).get();
		Usuario usuario = mapper.toEntity(input, Usuario.class);
		
		usuario.setNomeUsuario(usuarioExistente.getNomeUsuario());
		
		String passwdHash = BCrypt.hashpw(input.getSenha(), BCrypt.gensalt());
		usuario.setSenha(passwdHash);
		
		usuario = repository.save(usuario);
		
		UsuarioUpdateOutput output = mapper.toModel(usuario, UsuarioUpdateOutput.class);
		String token = jwtTokenUtil.generateToken(usuario.getNomeUsuario());
		
		JwtModel jwt = new JwtModel(token);
		output.setToken(jwt);
		
		return output;
	}
	
	public void descadastrar(Long userId) {
		this.repository.deleteById(userId);
	}
}
