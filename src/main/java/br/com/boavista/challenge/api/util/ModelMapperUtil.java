package br.com.boavista.challenge.api.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class ModelMapperUtil {

	@Autowired
	private ModelMapper mapper;
	
	public <E, M> E toEntity(M model, Class<E> clazz) {
		return mapper.map(model, clazz);
	}
	
	public <E, M> M toModel(E entity, Class<M> clazz) {
		return mapper.map(entity, clazz);
	}
}
