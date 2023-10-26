package dev.pack.modules.additional_prices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalPricesRepository extends JpaRepository<AdditionalPrices, Integer> {

    @Query("SELECT ap FROM AdditionalPrices ap WHERE ap.registrationPaths.id = :regisPathId ORDER BY ap.id ASC")
    List<AdditionalPrices> findAllByRegistrationPathsId(@Param("regisPathId") Integer regisPathId);

}
