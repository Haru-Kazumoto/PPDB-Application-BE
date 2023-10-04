package dev.pack.modules.user;

import dev.pack.modules.enums.Role;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(@Email String email);
    Optional<User> findByRole(Role role);
    List<User> findAllByRole(Role role);
    Integer countByRole(Role role);
}