package dev.pack.modules.user;

import java.util.List;
import java.util.Optional;

import dev.pack.modules.enums.Role;
import dev.pack.modules.ppdbFlow.PpdbFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);

  @Query("""
      SELECT dataFlow FROM PpdbFlow dataFlow WHERE dataFlow.userId.id = :userId
  """)
  List<PpdbFlow> findAllPpdbFlowByUserId(Integer userId);

  Integer countByRole(Role role);

}
