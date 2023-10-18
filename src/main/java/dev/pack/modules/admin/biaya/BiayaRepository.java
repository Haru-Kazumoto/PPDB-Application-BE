package dev.pack.modules.admin.biaya;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BiayaRepository extends JpaRepository<Biaya, Integer> {

    @Query("SELECT b FROM Biaya b WHERE b.biayaTambahanId.id = :id AND b.deletedAt IS NULL ORDER BY b.id ASC")
    List<Biaya> findAllByBiayaTambahanId(@Param("id") Integer id);

    @Query("SELECT b FROM Biaya b WHERE b.namaBiayaTambahan = :namaBiaya")
    Optional<Biaya> findByNamaBiayaTambahan(String namaBiaya);

}
