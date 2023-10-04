package dev.pack.modules.detail_pendaftar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailPendaftarRepository extends JpaRepository<DetailPendaftar, Integer> {
}
