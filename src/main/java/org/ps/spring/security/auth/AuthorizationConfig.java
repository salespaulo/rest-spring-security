package org.ps.spring.security.auth;

import static org.ps.spring.security.auth.AuthenticationEndpoint.LOGIN_PATH_ENDPOINT;
import static org.ps.spring.security.auth.AuthenticationEndpoint.REFRESH_PATH_ENDPOINT;

import org.ps.spring.security.auth.jwt.JwtAuthenticationProvider;
import org.ps.spring.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import org.ps.spring.security.auth.token.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthorizationConfig extends WebSecurityConfigurerAdapter {

	public static final String AUTHORIZATION_HEADER = "X-Authorization";

	public static final String TOKEN_PROTECTED_ENDPOINTS = "/**";

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private RestAuthenticationFailureHandler failureHandler;

	@Autowired
	private TokenExtractor tokenExtractor;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthFilter() throws Exception {
		final SecurityRequestMatcher matcher = new SecurityRequestMatcher(TOKEN_PROTECTED_ENDPOINTS, LOGIN_PATH_ENDPOINT);

		final JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(matcher,
				tokenExtractor, failureHandler);

		filter.setAuthenticationManager(this.authenticationManager);

		return filter;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // We don't need CSRF for JWT based authentication
		.exceptionHandling()
		.authenticationEntryPoint(this.authenticationEntryPoint)
		.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()
			.antMatchers(LOGIN_PATH_ENDPOINT).permitAll()
			.antMatchers(REFRESH_PATH_ENDPOINT).permitAll()
		.and().authorizeRequests()
			.antMatchers(TOKEN_PROTECTED_ENDPOINTS).authenticated()
		.and()
			.addFilterBefore(buildJwtTokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
