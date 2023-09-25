package dev.pack.modules.action;

import dev.pack.modules.authorization.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
    List<Action> findAllByIsBug(Boolean condition);
    List<Action> findAllByMethod(String method);
    List<Action> findAllByRoleAccess(Role roleAccess);
    Optional<Action> findByPath(String path);
}
