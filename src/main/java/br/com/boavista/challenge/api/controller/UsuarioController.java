package br.com.boavista.challenge.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.boavista.challenge.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.boavista.challenge.api.domain.exception.UsuarioNaoConfereException;
import br.com.boavista.challenge.api.domain.model.Usuario;
import br.com.boavista.challenge.api.domain.model.repository.UsuarioRepository;
import br.com.boavista.challenge.api.domain.service.UsuarioService;
import br.com.boavista.challenge.api.model.UsuarioInput;
import br.com.boavista.challenge.api.model.UsuarioOutput;
import br.com.boavista.challenge.api.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Usuarios")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@ApiOperation(value="Listar usuários cadastrados", notes = "Operação para listar usuários cadastrados na API. Apenas usuário com perfil ADMIN pode realizar a consulta.")
	@ApiImplicitParam(name="Authorization", value = "JWT Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT (header.payload.signature)" )
	@GetMapping
	public List<Usuario> listar() {
		String username = getUsernameFromToken();
		
		return service.listar(username);
	}

	@ApiOperation(value="Adicionar usuário", notes = "Operação para adicionar usuário na API.")
	@PostMapping
	@ResponseStatus
	public ResponseEntity<UsuarioOutput> adicionar(@Valid @RequestBody UsuarioInput input) {
		UsuarioOutput output = service.criar(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(output);
	}
	
	@ApiOperation(value="Atualizar usuário", notes = "Operação para atualizar informações do usuário na API. Necessário informar o token retornado na criação do usuário.")
	@ApiImplicitParam(name="Authorization", value = "JWT Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT (header.payload.signature)" )
	@PutMapping("/{usuarioId}")
	public ResponseEntity<UsuarioOutput> atualizar(@PathVariable String usuarioId, @Valid @RequestBody UsuarioInput input) {
		Usuario usuarioExistente = repository.findByNomeUsuario(usuarioId);
		
		String username = getUsernameFromToken();
		
		// Valida se o usuário existe
		if (usuarioExistente == null) {
			throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
		}
		
		// Valida se o usuário armazenado no token é o mesmo informado como recurso
		if (!usuarioExistente.getNomeUsuario().equalsIgnoreCase(username)) {
			throw new UsuarioNaoConfereException("Usuário não confere.");
		}
		
		input.setId(usuarioExistente.getId());
		input.setNomeUsuario(usuarioExistente.getNomeUsuario());
		
		UsuarioOutput output = service.alterar(input);
		
		return ResponseEntity.ok(output);
	}
	

	@ApiOperation(value="Remover usuário", notes = "Operação para remover informações do usuário na API. Necessário informar o token retornado na criação do usuário.")
	@ApiImplicitParam(name="Authorization", value = "JWT Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer JWT (header.payload.signature)" )
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(@PathVariable String usuarioId) {
		Usuario usuarioExistente = repository.findByNomeUsuario(usuarioId);
		
		String username = getUsernameFromToken();
		
		// Valida se o usuário existe
		if (usuarioExistente == null) {
			throw new EntidadeNaoEncontradaException("Usuário não encontrado.");
		}
		
		// Valida se o usuário armazenado no token é o mesmo informado como recurso
		if (!usuarioExistente.getNomeUsuario().equalsIgnoreCase(username)) {
			throw new UsuarioNaoConfereException("Usuário não confere.");
		}
		
		service.descadastrar(usuarioExistente.getId());
		
		return ResponseEntity.noContent().build();
	}
	
	private String getUsernameFromToken() {
		String authHeader = request.getHeader("Authorization");
		final String token = authHeader.substring(7);
		
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return username;
	}
}