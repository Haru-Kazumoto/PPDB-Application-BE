package dev.pack.modules.registration_batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationBatchRepository extends JpaRepository<RegistrationBatch, Integer> {



}
