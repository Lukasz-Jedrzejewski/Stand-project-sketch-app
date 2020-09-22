package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.repository.VerificationTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokenPurgeTask {

    private final VerificationTokenRepository tokenRepository;

    public TokenPurgeTask(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(cron = "* * 11 * * *")
    public void purgeExpired() {
        Date now = Date.from(Instant.now());

        tokenRepository.deleteAllExpiredSince(now);
    }
}
