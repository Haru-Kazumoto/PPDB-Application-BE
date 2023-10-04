package dev.pack.modules.jalur_pendaftaran;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JalurPendaftaranRepository extends JpaRepository<JalurPendaftaran, Integer> {
}
