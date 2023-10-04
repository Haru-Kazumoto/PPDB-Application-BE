package dev.pack.modules.kegiatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KegiatanRepository extends JpaRepository<Kegiatan, Integer> {
}
