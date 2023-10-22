package dev.pack.modules.registration_paths;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationPathsRepository extends JpaRepository<RegistrationPaths, Integer> {

}
