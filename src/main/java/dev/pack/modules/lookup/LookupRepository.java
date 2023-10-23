package dev.pack.modules.lookup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LookupRepository extends JpaRepository<Lookup, Integer> {

    @Query("SELECT l FROM Lookup l WHERE l.type = :type")
    Optional<Lookup> getLookupByType(@Param("type") String type);

}
