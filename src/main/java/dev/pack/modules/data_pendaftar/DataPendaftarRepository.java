package dev.pack.modules.data_pendaftar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPendaftarRepository extends JpaRepository<DataPendaftar, Integer> {
}
