package org.ps.spring.security.auth.resource;

import org.ps.spring.security.auth.jwt.RawJwtToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticationTokenResource extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 2877954820905567501L;

    private RawJwtToken rawAccessToken;
    private UserLoggedIn userLoggedIn;

    public AuthenticationTokenResource(final RawJwtToken unsafeToken) {
        super(null);
        this.rawAccessToken = unsafeToken;
        this.setAuthenticated(false);
    }

    public AuthenticationTokenResource(final UserLoggedIn userLoggedIn) {
        super(userLoggedIn.getAuthorities());

        this.userLoggedIn = userLoggedIn;

        super.setAuthenticated(true);
        this.eraseCredentials();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userLoggedIn;
    }

    @Override
    public void eraseCredentials() {        
        super.eraseCredentials();
        this.rawAccessToken = null;
    }
    
    public UserLoggedIn getUserLoggedIn() {
    	return (UserLoggedIn) this.getPrincipal();
    }
}