package br.com.boavista.challenge.api.domain.exception;

public class UsuarioNaoAutorizadoException extends NegocioBaseException {

	private static final long serialVersionUID = 4453325758433115687L;

	public UsuarioNaoAutorizadoException(String message) {
		super(message);
	}
}
