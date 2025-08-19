package vn.java.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.java.backend.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByVerificationCode(String code);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
