package dev.pack.utils;

import dev.pack.modules.admin.jalur_pendaftaran.JalurPendaftaran;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {

    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deletedAt = CURRENT_TIMESTAMP WHERE e.id = :id")
    void softDeleteById(@Param("id") Integer id);

    @Query("SELECT d FROm #{#entityName} d WHERE d.deletedAt IS NULL AND d.userId = :userId")
    List<JalurPendaftaran> findAllDataDeletedAtIsNull(Integer id);

}
