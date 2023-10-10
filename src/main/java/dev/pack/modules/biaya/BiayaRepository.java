package dev.pack.modules.biaya;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiayaRepository extends JpaRepository<Biaya, Integer> {

    @Query("SELECT b FROM Biaya b WHERE b.biayaTambahanId.id = :id")
    List<Biaya> findAllByBiayaTambahanId(Integer id);

    @Query("SELECT b FROM Biaya b WHERE b.namaBiayaTambahan = :namaBiaya")
    Biaya findByNamaBiayaTambahan(String namaBiaya);

}
