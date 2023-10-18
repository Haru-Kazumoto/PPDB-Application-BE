package dev.pack.modules.admin.kegiatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KegiatanRepository extends JpaRepository<Kegiatan, Integer> {

    @Query("SELECT g FROM Kegiatan g WHERE g.deletedAt IS NULL AND g.gelombangId.id = :gelombangId ORDER BY g.id ASC")
    List<Kegiatan> findAllByGelombangId(Integer gelombangId);

}
