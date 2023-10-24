package dev.pack.modules.registration_paths;

import dev.pack.modules.enums.FormPurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationPathsRepository extends JpaRepository<RegistrationPaths, Integer> {

    @Query("SELECT t FROM RegistrationPaths t WHERE t.type = :type")
    List<RegistrationPaths> findAllByType(@Param("type") FormPurchaseType type);

}
