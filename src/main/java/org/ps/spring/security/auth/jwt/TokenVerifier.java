package org.ps.spring.security.auth.jwt;

import java.util.Optional;

import org.ps.spring.security.user.domain.User;

public interface TokenVerifier <T extends JwtToken> {

	public Optional<User> verify(T token);

}