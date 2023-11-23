package dev.pack.modules.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
      select t from Token t inner join User u\s
      on t.userId.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
    """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t WHERE (t.expired = true OR t.revoked = true) AND t.updatedAt <= :date")
    List<Token> findAllByExpiredOrRevokedBeforeDate(@Param("date") LocalDateTime date);
}
