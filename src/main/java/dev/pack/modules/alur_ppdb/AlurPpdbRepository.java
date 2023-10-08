package dev.pack.modules.alur_ppdb;

import dev.pack.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlurPpdbRepository extends JpaRepository<AlurPpdb, Integer> {
    @Query("SELECT p FROM AlurPpdb p WHERE p.userId.id = :userId")
    List<AlurPpdb> findAllByUserId(Integer userId);

    Optional<AlurPpdb> findByTitle(String title);
}
