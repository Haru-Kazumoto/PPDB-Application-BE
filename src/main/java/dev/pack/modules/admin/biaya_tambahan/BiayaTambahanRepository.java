package dev.pack.modules.admin.biaya_tambahan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiayaTambahanRepository extends JpaRepository<BiayaTambahan, Integer> {

    @Query("SELECT j FROM BiayaTambahan j WHERE j.deletedAt IS NULL AND j.jalurPendaftaranId.id = :jalurPendaftaranId ORDER BY j.id ASC")
    List<BiayaTambahan> findAllByJalurPendaftaranId(@Param("jalurPendaftaranId") Integer jalurPendaftaranId);

}
