package dev.pack.modules.pendaftar_ppdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendaftarPpdbRepository extends JpaRepository<PendaftarPpdb, Integer> {
}
