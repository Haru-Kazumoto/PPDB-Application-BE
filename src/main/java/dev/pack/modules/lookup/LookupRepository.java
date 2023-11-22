package dev.pack.modules.lookup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LookupRepository extends JpaRepository<Lookup, Integer> {

    @Query("SELECT l FROM Lookup l WHERE l.type = :type")
    List<Lookup> getLookupByType(@Param("type") String type);

    @Query("SELECT m FROM Lookup m WHERE m.type = 'MAJOR'")
    List<Lookup> findAllMajor();

    @Query("SEleCT m FROM Lookup m WHERE m.value = :major")
    Optional<Lookup> findMajor(@Param("major") String major);

    @Query("""
        SELECT l from Lookup l where l.value = :value
""")
    Optional<Lookup> getByValue(String value);

}
