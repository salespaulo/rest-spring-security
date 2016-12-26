package org.ps.spring.security.core.exception;

import org.ps.spring.security.auth.jwt.JwtToken;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtTokenExpiredException extends JwtTokenAuthenticationException {

    private static final String JWT_TOKEN_EXPIRED = "JWT Token expired";

	private static final long serialVersionUID = -5959543783324224864L;
    
    public JwtTokenExpiredException(final JwtToken token, final ExpiredJwtException cause) {
        super(token, JWT_TOKEN_EXPIRED, cause);
    }

}