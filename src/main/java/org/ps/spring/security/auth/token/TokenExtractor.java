package org.ps.spring.security.auth.token;

public interface TokenExtractor {

	String extract(final String header);

}
