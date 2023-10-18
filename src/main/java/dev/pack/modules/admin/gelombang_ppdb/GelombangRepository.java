package dev.pack.modules.admin.gelombang_ppdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GelombangRepository extends JpaRepository<Gelombang, Integer> {

    @Query("SELECT g FROM Gelombang g WHERE g.deletedAt IS NULL AND g.jalurPendaftaranId.id = :jalurPendaftaranId ORDER BY g.id ASC")
    List<Gelombang> findAllByJalurPendaftaranId(@Param("jalurPendaftaranId") Integer jalurPendaftaranId);

}
