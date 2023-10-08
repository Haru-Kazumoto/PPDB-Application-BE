package dev.pack.modules.jalur_pendaftaran;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JalurPendaftaranRepository extends JpaRepository<JalurPendaftaran, Integer> {

    Optional<JalurPendaftaran> findByNamaJalurPendaftaran(@NonNull String namaJalurPendaftaran);

    @Query("SELECT u FROM JalurPendaftaran u WHERE u.userId.id = :userId")
    List<JalurPendaftaran> findAllByUserId(Integer userId);
}
