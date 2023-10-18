package dev.pack.modules.admin.keterangan;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeteranganRepository extends JpaRepository<Keterangan, Integer> {

    @Query("SELECT j FROM Keterangan j WHERE j.deletedAt IS NULL AND j.jalurPendaftaranId.id = :id ORDER BY j.id ASC")
    List<Keterangan> findAllByJalurPendaftaranId(@Param("id") Integer id);

    @Query("SELECT d FROM Keterangan d WHERE d.deletedAt IS NOT NULL AND d.jalurPendaftaranId.id = :id ORDER BY d.id ASC")
    List<Keterangan> findAllDataHasBeenDeleted(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Keterangan j SET j.deletedAt = CURRENT_TIMESTAMP WHERE j.id = :id")
    void softDeleteById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Keterangan j SET j.deletedAt = NULL WHERE j.id = :id")
    Keterangan restoreData(@Param("id") Integer id);
}
