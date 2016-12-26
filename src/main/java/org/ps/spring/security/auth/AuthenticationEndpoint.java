package org.ps.spring.security.auth;

import org.ps.spring.security.auth.jwt.JwtSettings;
import org.ps.spring.security.auth.jwt.RawJwtToken;
import org.ps.spring.security.auth.jwt.RefreshAccessJwtToken;
import org.ps.spring.security.auth.resource.LoginTokenResource;
import org.ps.spring.security.core.exception.JwtTokenInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationEndpoint {

	public static final String LOGIN_PATH_ENDPOINT = "/auth/login";
	public static final String REFRESH_PATH_ENDPOINT = "/auth/token";
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtSettings settings;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public @ResponseBody LoginTokenResource login(
    		@RequestHeader("username") final String username, 
    		@RequestHeader("password") final String password) {

		return authenticationService.login(username, password);
    }

    @Secured("ROLE_REFRESH_TOKEN")
	@RequestMapping(value="/token", method=RequestMethod.POST)
    public @ResponseBody LoginTokenResource refresh(
    		@RequestHeader("refresh-token") final String token) {

        return authenticationService.tokenRefresh(getRefreshAccessToken(token));
    }

	private RefreshAccessJwtToken getRefreshAccessToken(final String token) {
		final String signingKey = settings.getTokenSigningKey();
        final RawJwtToken rawToken = new RawJwtToken(token);
		return RefreshAccessJwtToken.of(rawToken, signingKey)
        		.orElseThrow(() -> new JwtTokenInvalidException(rawToken));
	}

}