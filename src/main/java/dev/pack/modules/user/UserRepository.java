package dev.pack.modules.user;

import dev.pack.modules.admin.enums.Role;
import jakarta.validation.constraints.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    Optional<User> findByEmail(@Email String email);

    Optional<User> findByRole(Role role);

    List<User> findAllByRole(Role role);

    Integer countByRole(Role role);

    @Query("SELECT u FROM User u WHERE u.deletedAt IS NULL ORDER BY u.id ASC")
    Page<User> findAllActive(Pageable pageable);
}