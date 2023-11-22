package dev.pack;

import dev.pack.modules.token.Token;
import dev.pack.modules.token.TokenCleanupScheduler;
import dev.pack.modules.token.TokenRepository;
import dev.pack.modules.token.TokenType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class TokenSchedulerTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenCleanupScheduler tokenCleanupScheduler;

    @Test
    void testCleanTokenScheduler() {
        //MOCK DATA
        Token token1 = Token.builder()
                .id(1)
                .tokenType(TokenType.BEARER)
                .expired(true)
                .revoked(true)
                .build();

        Token token2 = Token.builder()
                .id(2)
                .tokenType(TokenType.BEARER)
                .expired(true)
                .revoked(true)
                .build();

        Token token3 = Token.builder()
                .id(3)
                .tokenType(TokenType.BEARER)
                .expired(true)
                .revoked(true)
                .build();

        List<Token> expiredOrRevokedTokens = Arrays.asList(token1,token2,token3);

        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1L);

        Mockito.when(this.tokenRepository.findAllByExpiredOrRevokedBeforeDate(Mockito.any(LocalDateTime.class)))
                .thenReturn(expiredOrRevokedTokens);

        this.tokenCleanupScheduler.cleanUpExpiredAndRevokedTokens();

        Mockito.verify(tokenRepository, Mockito.times(1)).deleteAll(expiredOrRevokedTokens);
    }
}
