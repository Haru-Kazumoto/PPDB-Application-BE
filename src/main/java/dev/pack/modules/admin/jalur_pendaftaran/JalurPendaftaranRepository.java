package dev.pack.modules.admin.jalur_pendaftaran;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JalurPendaftaranRepository extends JpaRepository<JalurPendaftaran, Integer> {

    @Query("SELECT n FROM JalurPendaftaran n WHERE n.namaJalurPendaftaran = :namaJalurPendaftaran ORDER BY n.id ASC")
    Optional<JalurPendaftaran> findByNamaJalurPendaftaran(@NonNull String namaJalurPendaftaran);

    @Query("SELECT u FROM JalurPendaftaran u WHERE u.deletedAt IS NULL AND u.userId.id = :userId ORDER BY u.id ASC")
    List<JalurPendaftaran> findAllByUserId(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE JalurPendaftaran j SET j.deletedAt = CURRENT_TIMESTAMP WHERE j.id = :id")
    void softDeleteById(@Param("id") Integer id);

    @Query("SELECT d FROM JalurPendaftaran d WHERE d.deletedAt IS NOT NULL AND d.userId.id = :userId ORDER BY d.id ASC")
    List<JalurPendaftaran> findAllByDeletedAtIsNull(@Param("userId") Integer userId);

    @Query("UPDATE JalurPendaftaran j SET j.deletedAt = NULL WHERE j.id = :id")
    Optional<JalurPendaftaran> restoreData(@Param("id") Integer id);
}
