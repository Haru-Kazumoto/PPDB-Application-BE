package dev.pack.modules.alur_ppdb;

import dev.pack.modules.enums.Grade;
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

    @Query("SELECT t FROM AlurPpdb t WHERE t.title = :title")
    Optional<AlurPpdb> findByTitle(@Param("title") String title);

    @Query("SELECT a FROM AlurPpdb a WHERE a.grade = :grade")
    List<AlurPpdb> findAllByGrade(@Param("grade")Grade grade);

    @Transactional
    @Modifying
    @Query("UPDATE AlurPpdb j SET j.deletedAt = CURRENT_TIMESTAMP WHERE j.id = :id")
    void softDeleteById(Integer id);
}
