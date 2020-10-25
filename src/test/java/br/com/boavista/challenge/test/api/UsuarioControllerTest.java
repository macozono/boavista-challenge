package br.com.boavista.challenge.test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.boavista.challenge.api.BoavistaChallengeApplication;
import br.com.boavista.challenge.api.domain.model.TipoPerfil;
import br.com.boavista.challenge.api.model.EnderecoModel;
import br.com.boavista.challenge.api.model.PerfilModel;
import br.com.boavista.challenge.api.model.UsuarioInput;
import br.com.boavista.challenge.api.model.UsuarioOutput;

@SpringBootTest(classes = BoavistaChallengeApplication.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mvc;
	
	private String uri = "/usuarios";
	
	@Test
	public void validateUserCreate() throws Exception {
		UsuarioInput input = generateInput();
		input.setNomeUsuario("macozono");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		UsuarioOutput output = jsonToObject(content, UsuarioOutput.class);
		
		assertEquals(output.getNomeUsuario(), input.getNomeUsuario());
	}
	
	@Test
	public void validateUserUpdate() throws Exception {
		UsuarioInput input = generateInput();
		input.setNomeUsuario("macozono2");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		UsuarioOutput output = jsonToObject(content, UsuarioOutput.class);
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri.concat("/").concat(output.getNomeUsuario()))
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", "Bearer " + output.getJwt().getToken())
				.content(this.mapToJson(input)))
				.andReturn();
		
		String contentUpdate = mvcResult.getResponse().getContentAsString();
		UsuarioOutput outputUpdate = jsonToObject(contentUpdate, UsuarioOutput.class);
		assertEquals(output.getNomeUsuario(), outputUpdate.getNomeUsuario());
	}
	
	@Test
	public void validateUsersList() throws Exception {
		UsuarioInput input = generateInput();
		input.setNomeUsuario("macozono3");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		UsuarioOutput output = jsonToObject(content, UsuarioOutput.class);
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", "Bearer " + output.getJwt().getToken())
				.content(this.mapToJson(input)))
				.andReturn();
		
		String contentList = mvcResult.getResponse().getContentAsString();
		jsonToObject(contentList, ArrayList.class);
	}
	
	@Test
	public void validateUserDelete() throws Exception {
		UsuarioInput input = generateInput();
		input.setNomeUsuario("macozono4");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		UsuarioOutput output = jsonToObject(content, UsuarioOutput.class);
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri.concat("/").concat(output.getNomeUsuario()))
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", "Bearer " + output.getJwt().getToken())
				.content(this.mapToJson(input)))
				.andReturn();

		assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void validateUsersCreateEmpty() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void validateUsersListUnauthorized() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		assertEquals(HttpStatus.UNAUTHORIZED.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void validateUserUpdateUnauthorized() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		assertEquals(HttpStatus.UNAUTHORIZED.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void validateUserDeleteUnauthorized() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		assertEquals(HttpStatus.UNAUTHORIZED.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void validateUserNotFound() throws Exception {
		UsuarioInput input = generateInput();
		input.setNomeUsuario("macozono5");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		UsuarioOutput output = jsonToObject(content, UsuarioOutput.class);
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri.concat("/").concat("batata"))
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", "Bearer " + output.getJwt().getToken())
				.content(this.mapToJson(input)))
				.andReturn();
		
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void validateUserInvalidResourceUsername() throws Exception {
		UsuarioInput input = generateInput();
		input.setNomeUsuario("macozono6");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		input.setNomeUsuario("macozono7");
		
		String content = mvcResult.getResponse().getContentAsString();
		UsuarioOutput output = jsonToObject(content, UsuarioOutput.class);
		
		String token = output.getJwt().getToken();
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.mapToJson(input)))
				.andReturn();
		
		content = mvcResult.getResponse().getContentAsString();
		output = jsonToObject(content, UsuarioOutput.class);
		
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri.concat("/").concat(output.getNomeUsuario()))
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", "Bearer " + token)
				.content(this.mapToJson(input)))
				.andReturn();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}

	private UsuarioInput generateInput() {
		UsuarioInput input = new UsuarioInput();
		EnderecoModel endereco = new EnderecoModel();
		
		input.setDtNascimento(Calendar.getInstance().getTime());
		input.setEmail("teste@tst.com.br");
		input.setNome("Marcel Cozono");
		input.setSenha("teste001");
		input.setTelefone(11992024086L);
		
		endereco.setCep("06213040");
		endereco.setCidade("Osasco");
		endereco.setComplemento("apto 74 torre 1");
		endereco.setNumero(323);
		endereco.setUf("SP");
		endereco.setLogradouro("Rua Zuma de Sá Fernandes");
		
		input.setEndereco(endereco);
		input.setPerfil(new PerfilModel());
		input.getPerfil().setId(1L);
		input.getPerfil().setTipoPerfil(TipoPerfil.ADMIN);
		
		return input;
	}

	private <T> String mapToJson(T obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	private <T> T jsonToObject(String content, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(content, clazz);
	}
}