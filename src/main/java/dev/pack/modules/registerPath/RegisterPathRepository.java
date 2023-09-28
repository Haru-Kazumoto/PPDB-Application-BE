package dev.pack.modules.registerPath;

import dev.pack.modules.registerPath.RegisterPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterPathRepository extends JpaRepository<RegisterPath, Integer> {

}
