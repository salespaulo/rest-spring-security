package org.ps.spring.security.auth.resource;

import org.ps.spring.security.auth.jwt.JwtToken;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class LoginTokenResource {

	private JwtToken accessToken;
	
	private JwtToken refreshToken;
	
}
