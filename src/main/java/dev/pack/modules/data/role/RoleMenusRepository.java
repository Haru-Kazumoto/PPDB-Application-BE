package dev.pack.modules.newdata.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenusRepository extends JpaRepository<RolesMenus, Integer> {


}