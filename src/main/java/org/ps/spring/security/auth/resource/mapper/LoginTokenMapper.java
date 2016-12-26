package org.ps.spring.security.auth.resource.mapper;

import java.util.function.Function;

import org.ps.spring.security.auth.jwt.AccessJwtTokenFactory;
import org.ps.spring.security.auth.jwt.JwtToken;
import org.ps.spring.security.auth.resource.LoginTokenResource;
import org.ps.spring.security.auth.resource.UserLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginTokenMapper {

	private final AccessJwtTokenFactory tokenFactory;

	@Autowired
	public LoginTokenMapper(final AccessJwtTokenFactory tokenFactory) {
		super();
		this.tokenFactory = tokenFactory;
	}

	public Function<UserLoggedIn, LoginTokenResource> map() {
		return this::build;
	}
	
	private LoginTokenResource build(final UserLoggedIn userLoggedIn) {
		final JwtToken accessToken = tokenFactory.createAccessJwtToken(userLoggedIn);
		final JwtToken refreshAccessToken = tokenFactory.createRefreshAccessJwtToken(userLoggedIn);

		return new LoginTokenResource(accessToken, refreshAccessToken);
	}
	
}
