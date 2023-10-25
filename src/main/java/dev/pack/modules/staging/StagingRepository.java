package dev.pack.modules.staging;

import dev.pack.modules.staging.Staging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface StagingRepository extends JpaRepository<Staging, Integer> {


    @Query("""
        select s from Staging s where s.name like CONCAT('%', CONCAT(:name, '%'))
""")
    Optional<Staging> findByName(String name);

}
