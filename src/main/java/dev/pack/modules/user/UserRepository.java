package dev.pack.modules.user;

import java.util.Optional;

import dev.pack.modules.authorization.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);

  Integer countByRole(Role role);

}
