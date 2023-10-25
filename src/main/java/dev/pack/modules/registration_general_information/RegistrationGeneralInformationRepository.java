package dev.pack.modules.registration_general_information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationGeneralInformationRepository extends JpaRepository<RegistrationGeneralInformation, Integer> {

    @Query(
            value = """
                SELECT r FROM RegistrationGeneralInformation r WHERE r.registrationPaths.id = :regisPathsId ORDER BY r.id ASC
            """
    )
    List<RegistrationGeneralInformation> findAllByRegisPathId(@Param("regisPathsId") Integer regisPathsId);

}
