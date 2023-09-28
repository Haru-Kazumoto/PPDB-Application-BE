package dev.pack.modules.registerPath.publicInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicInformationRepository extends JpaRepository<PublicInformation, Integer> {
}
