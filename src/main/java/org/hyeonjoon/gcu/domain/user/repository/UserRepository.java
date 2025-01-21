package org.hyeonjoon.gcu.domain.user.repository;

import org.hyeonjoon.gcu.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

    Users findByUsername(String username);

    boolean existsByUsername(String username);
}
