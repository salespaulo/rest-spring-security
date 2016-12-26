package org.ps.spring.security.core.exception;

import org.ps.spring.security.auth.jwt.JwtToken;

public class JwtTokenInvalidException extends JwtTokenAuthenticationException {

	private static final String JWT_TOKEN_INVALID = "Jwt Token Invalid.";

	private static final long serialVersionUID = -294671188037098603L;

    public JwtTokenInvalidException(final JwtToken token) {
		super(token, JWT_TOKEN_INVALID);
	}
    
}