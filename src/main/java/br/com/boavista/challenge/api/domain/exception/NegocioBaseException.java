package br.com.boavista.challenge.api.domain.exception;

public class NegocioBaseException extends RuntimeException {

	private static final long serialVersionUID = 7129789533547050643L;

	public NegocioBaseException(String message) {
		super(message);
	}
}
