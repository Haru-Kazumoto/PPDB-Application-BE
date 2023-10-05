package dev.pack.modules.keterangan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeteranganRepository extends JpaRepository<Keterangan, Integer> {
}
