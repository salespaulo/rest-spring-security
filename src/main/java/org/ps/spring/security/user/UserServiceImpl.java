package org.ps.spring.security.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.ps.spring.security.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(final UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public Optional<User> getByEmailOptional(final String username) {
		return repository.findOneByUsername(username);
	}

	@Override
	public User getByEmail(final String username) {
		return this.getByEmailOptional(username)
				.orElseThrow(() -> new RuntimeException("Username not found: " + username));
	}

	@Override
	public User save(final User user) {
		return repository.saveAndFlush(user);
	}

}
