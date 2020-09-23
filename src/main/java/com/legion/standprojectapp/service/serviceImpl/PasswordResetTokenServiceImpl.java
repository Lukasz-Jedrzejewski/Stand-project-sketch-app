package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.PasswordResetToken;
import com.legion.standprojectapp.repository.PasswordResetTokenRepository;
import com.legion.standprojectapp.service.PasswordResetTokenService;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public PasswordResetToken findToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.save(passwordResetToken);
    }
}
