package br.com.boavista.challenge.api.domain.exception;

public class UsuarioNaoConfereException extends NegocioBaseException {

	private static final long serialVersionUID = -3270891412916087651L;

	public UsuarioNaoConfereException(String message) {
		super(message);
	}
}
