package org.hyeonjoon.gcu.domain.auth.repository;

import org.hyeonjoon.gcu.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
