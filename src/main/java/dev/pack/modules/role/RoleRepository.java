package dev.pack.modules.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    @Query("""
      select r from Roles r where r.role_name like '%:name%'
    """)
    Roles findRolesByName(String name);
}