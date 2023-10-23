package dev.pack.modules.registration_batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationBatchRepository extends JpaRepository<RegistrationBatch, Integer> {

    @Query("""
            SELECT rb.id,\s
            COUNT(s) AS countStudent FROM RegistrationBatch rb\s
            LEFT JOIN rb.students s GROUP BY rb.id
    """)
    List<Object[]> findTotalPendaftarPerBatch();

}
