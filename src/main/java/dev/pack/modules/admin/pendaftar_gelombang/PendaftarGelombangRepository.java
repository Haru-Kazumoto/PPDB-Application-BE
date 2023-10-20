package dev.pack.modules.admin.pendaftar_gelombang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PendaftarGelombangRepository extends JpaRepository<PendaftarGelombang, Integer> {

}
