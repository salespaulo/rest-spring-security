package org.ps.spring.security.user;

import java.util.Optional;

import org.ps.spring.security.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByUsername(final String username);
	
}
