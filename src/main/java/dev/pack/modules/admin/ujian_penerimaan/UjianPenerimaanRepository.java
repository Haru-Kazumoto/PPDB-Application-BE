package dev.pack.modules.admin.ujian_penerimaan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UjianPenerimaanRepository extends JpaRepository<UjianPenerimaan, Integer> {
}
