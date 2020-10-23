package br.com.boavista.challenge.api.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioBaseException {

	private static final long serialVersionUID = 3007711987279487908L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}