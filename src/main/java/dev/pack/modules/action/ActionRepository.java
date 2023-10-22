package dev.pack.modules.action;

import dev.pack.modules.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
    Optional<Action> findByPath(String path);
    List<Action> findAllByMethod(String method);
    List<Action> findAllByRoleAccess(Role role);
    List<Action> findAllByIsBug(Boolean condition);
}
