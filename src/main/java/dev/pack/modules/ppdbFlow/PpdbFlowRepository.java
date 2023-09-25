package dev.pack.modules.ppdbFlow;

import dev.pack.modules.user.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PpdbFlowRepository extends JpaRepository<PpdbFlow, Integer> {

    Optional<PpdbFlow> findByTitle(@NonNull String title);

    @Query("""
        SELECT p FROM PpdbFlow p WHERE p.userId.id = :userId
    """)
    List<PpdbFlow> findAllByUserId(@Param("userId") Integer userId);
}
