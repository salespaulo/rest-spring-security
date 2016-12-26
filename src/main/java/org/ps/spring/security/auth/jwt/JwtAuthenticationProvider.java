package org.ps.spring.security.auth.jwt;

import java.util.List;

import org.ps.spring.security.auth.resource.AuthenticationTokenResource;
import org.ps.spring.security.auth.resource.UserLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtSettings settings;
	
	@Autowired
	public JwtAuthenticationProvider(final JwtSettings settings) {
		this.settings = settings;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final AuthenticationTokenResource authToken = (AuthenticationTokenResource) authentication;
		final RawJwtToken rawToken = (RawJwtToken) authToken.getCredentials();

		final Jws<Claims> jwsClaims = rawToken.parse(settings.getTokenSigningKey());

		final String subject = jwsClaims.getBody().getSubject();

		@SuppressWarnings("unchecked")
		final List<String> privileges = jwsClaims.getBody().get("privileges", List.class);

		return new AuthenticationTokenResource(new UserLoggedIn(subject, Sets.newHashSet(privileges)));
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return AuthenticationTokenResource.class.isAssignableFrom(authentication);
	}
	
}