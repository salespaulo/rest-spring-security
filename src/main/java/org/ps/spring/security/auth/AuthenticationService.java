package org.ps.spring.security.auth;

import java.util.Optional;
import java.util.function.Predicate;

import org.ps.spring.security.auth.jwt.RefreshAccessJwtToken;
import org.ps.spring.security.auth.jwt.RefreshTokenVerifier;
import org.ps.spring.security.auth.resource.LoginTokenResource;
import org.ps.spring.security.auth.resource.UserLoggedIn;
import org.ps.spring.security.auth.resource.mapper.LoginTokenMapper;
import org.ps.spring.security.core.exception.JwtTokenInvalidException;
import org.ps.spring.security.core.exception.UsernamePasswordAuthenticationException;
import org.ps.spring.security.user.UserService;
import org.ps.spring.security.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {

	private final UserService userService;

	private final LoginTokenMapper loginTokenMapper;

	private final RefreshTokenVerifier tokenVerifier;

	private final PasswordEncoder encoder;

	@Autowired
	public AuthenticationService(final UserService userService, final LoginTokenMapper loginTokenMapper,
			final RefreshTokenVerifier tokenVerifier, final PasswordEncoder encoder) {
		this.loginTokenMapper = loginTokenMapper;
		this.userService= userService;
		this.tokenVerifier = tokenVerifier;
		this.encoder = encoder;
	}
	
	public LoginTokenResource login(String username, String password) {
		final Optional<User> user = userService.getByEmailOptional(username);

		final LoginTokenResource loginToken = user
				.filter(authenticated(password))
				.map(UserLoggedIn::new)
				.map(loginTokenMapper.map())
				.orElseThrow(() -> new UsernamePasswordAuthenticationException());

		final User rawUser = user.get();
		rawUser.setRefreshToken(loginToken.getRefreshToken().getToken());
		userService.save(rawUser);
		
		return loginToken;
	}

	public LoginTokenResource tokenRefresh(final RefreshAccessJwtToken refreshToken) {
		return tokenVerifier.verify(refreshToken)
			.map(UserLoggedIn::new)
			.map(loginTokenMapper.map())
			.orElseThrow(() -> new JwtTokenInvalidException(refreshToken));
	}

    private Predicate<User> authenticated(final String password) {
    	return user -> encoder.matches(password, user.getPassword()) && withPrivileges(user);
    }

    private boolean withPrivileges(final User user) {
		return ! user.getPrivileges().isEmpty();
	}
    
    public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("Test"));
	}
    
}
