package dev.pack.modules.token;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {

    private final TokenRepository tokenRepository;

    @Scheduled(cron = "0 0 0 * * *") //Run daily at midnight 00:00
    @Transactional
    public void cleanUpExpiredAndRevokedTokens(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusWeeks(1);

        List<Token> expiredOrRevokedTokens = this.tokenRepository.findAllByExpiredOrRevokedBeforeDate(oneWeekAgo);

        tokenRepository.deleteAll(expiredOrRevokedTokens);
    }

}
