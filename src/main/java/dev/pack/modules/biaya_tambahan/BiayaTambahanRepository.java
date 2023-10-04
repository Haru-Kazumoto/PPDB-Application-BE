package dev.pack.modules.biaya_tambahan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiayaTambahanRepository extends JpaRepository<BiayaTambahan, Integer> {
}
