package org.ps.spring.security.auth.jwt;

import java.util.Optional;

import org.ps.spring.security.user.UserService;
import org.ps.spring.security.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenVerifier implements TokenVerifier<RefreshAccessJwtToken> {

	private final UserService userService;

	private final JwtSettings settings;

	@Autowired
	public RefreshTokenVerifier(final UserService userService, JwtSettings settings) {
		this.userService = userService;
		this.settings = settings;
	}
	
    @Override
    public Optional<User> verify(final RefreshAccessJwtToken token) {
    	final String subject = token.getSubject();
    	final String tokenJti = token.getJti();
    	final String signingKey = settings.getTokenSigningKey();

    	final Optional<User> user = userService.getByEmailOptional(subject);

    	final boolean isValid = user.map(User::getRefreshToken)
				.map(RawJwtToken::new)
				.flatMap(raw -> RefreshAccessJwtToken.of(raw, signingKey))
				.map(RefreshAccessJwtToken::getJti)
				.map(userJti -> userJti.equals(tokenJti))
				.orElse(false);

    	return isValid ? user : Optional.empty();
    }

}
