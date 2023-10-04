package dev.pack.modules.informasi_umum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformasiUmumRepository extends JpaRepository<InformasiUmum, Integer> {
}
