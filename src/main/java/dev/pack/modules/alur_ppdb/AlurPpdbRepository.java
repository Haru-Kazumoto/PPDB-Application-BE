package dev.pack.modules.alur_ppdb;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlurPpdbRepository extends JpaRepository<AlurPpdb, Integer> {
    @Query("SELECT p FROM AlurPpdb p WHERE p.userId.id = :userId")
    List<AlurPpdb> findAllByUserId(Integer userId);

    @Query("SELECT t FROM AlurPpdb t WHERE t.title = :title")
    Optional<AlurPpdb> findByTitle(@Param("title") String title);

    @Transactional
    @Modifying
    @Query("UPDATE AlurPpdb j SET j.deletedAt = CURRENT_TIMESTAMP WHERE j.id = :id")
    void softDeleteById(Integer id);

    @Query("SELECT d FROM AlurPpdb d WHERE d.deletedAt IS NULL AND d.userId.id = :id")
    List<AlurPpdb> indexDeleted(@Param("id") Integer id);

    @Query("SELECT d FROM AlurPpdb d WHERE d.deletedAt IS NULL")
    List<AlurPpdb> indexDeleted();
}
