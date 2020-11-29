package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.repository.EmailChangeTokenRepository;
import com.legion.standprojectapp.repository.PasswordChangeTokenRepository;
import com.legion.standprojectapp.repository.PasswordResetTokenRepository;
import com.legion.standprojectapp.repository.VerificationTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokenPurgeTask {

    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordChangeTokenRepository passwordChangeTokenRepository;
    private final EmailChangeTokenRepository emailChangeTokenRepository;

    public TokenPurgeTask(VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, PasswordChangeTokenRepository passwordChangeTokenRepository, EmailChangeTokenRepository emailChangeTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordChangeTokenRepository = passwordChangeTokenRepository;
        this.emailChangeTokenRepository = emailChangeTokenRepository;
    }

    @Scheduled(cron = "* * 11 * * *")
    public void purgeExpired() {
        Date now = Date.from(Instant.now());

        verificationTokenRepository.deleteAllExpiredSince(now);
        passwordResetTokenRepository.deleteAllExpiredSince(now);
        passwordChangeTokenRepository.deleteAllExpiredSince(now);
        emailChangeTokenRepository.deleteAllExpiredSince(now);
    }
}
