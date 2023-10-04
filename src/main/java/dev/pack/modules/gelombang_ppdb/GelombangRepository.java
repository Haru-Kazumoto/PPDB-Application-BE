package dev.pack.modules.gelombang_ppdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GelombangRepository extends JpaRepository<Gelombang, Integer> {
}
