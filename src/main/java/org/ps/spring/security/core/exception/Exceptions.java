package org.ps.spring.security.core.exception;

import java.io.Serializable;
import java.util.function.Supplier;

import org.ps.spring.security.auth.jwt.RawJwtToken;
import org.ps.spring.security.user.domain.User;
import org.springframework.security.authentication.BadCredentialsException;

import io.jsonwebtoken.ExpiredJwtException;

public final class Exceptions {
	
	private Exceptions() {
		throw new AssertionError();
	}

	public static Supplier<ResourceNotFoundException> supplierUserNotFound(final Serializable id) {
		return supplierResourceNotFound(User.class.getSimpleName(), id);
	}
	
	public static Supplier<ResourceNotFoundException> supplierResourceNotFound(final String name, final Serializable id) {
		return ResourceNotFoundException.supplier(name, id);
	}

	public static Supplier<JwtTokenMissingException> supplierJwtTokenMissing() {
		return () -> new JwtTokenMissingException();
	}

	public static BadCredentialsException newBadCredentials(final Throwable cause) {
		return new BadCredentialsException("Bad Credentials Authorization.", cause);
	}

	public static JwtTokenExpiredException newJwtTokenExpired(final RawJwtToken token, final ExpiredJwtException cause) {
		return new JwtTokenExpiredException(token, cause);
	}

}
