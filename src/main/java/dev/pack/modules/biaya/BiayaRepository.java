package dev.pack.modules.biaya;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiayaRepository extends JpaRepository<Biaya, Integer> {
}
