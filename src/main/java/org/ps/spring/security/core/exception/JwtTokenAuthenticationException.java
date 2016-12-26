package org.ps.spring.security.core.exception;

import org.ps.spring.security.auth.jwt.JwtToken;
import org.springframework.security.core.AuthenticationException;

public class JwtTokenAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 3018461054095289405L;

	private final JwtToken token;
	
	protected JwtTokenAuthenticationException(JwtToken token, String msg) {
		super(msg);
		this.token = token;
	}
	
	protected JwtTokenAuthenticationException(JwtToken token, String msg, Throwable cause) {
		super(msg, cause);
		this.token = token;
	}
	
	public JwtToken getToken() {
		return token;
	}
	
}
