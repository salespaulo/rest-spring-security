package org.ps.spring.security.user;

import java.util.Optional;

import org.ps.spring.security.user.domain.User;

public interface UserService {

	Optional<User> getByEmailOptional(final String username);

	User getByEmail(final String username);

	User save(final User user);

}
