package br.com.boavista.challenge.api.domain.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 2190598265090466533L;

	public JwtAuthenticationException(String msg) {
		super(msg);
	}
	
	public JwtAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
