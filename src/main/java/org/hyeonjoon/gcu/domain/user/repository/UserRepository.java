package org.hyeonjoon.gcu.domain.user.repository;

import org.hyeonjoon.gcu.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    Users findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Users> findByEmail(String email);

    boolean existsByNickname(String userName);

    boolean findByUsernameIsNull();
}
