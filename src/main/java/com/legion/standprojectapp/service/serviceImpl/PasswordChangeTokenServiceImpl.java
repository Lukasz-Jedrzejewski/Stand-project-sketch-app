package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.PasswordChangeToken;
import com.legion.standprojectapp.repository.PasswordChangeTokenRepository;
import com.legion.standprojectapp.service.PasswordChangeTokenService;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeTokenServiceImpl implements PasswordChangeTokenService {

    private final PasswordChangeTokenRepository passwordChangeTokenRepository;

    public PasswordChangeTokenServiceImpl(PasswordChangeTokenRepository passwordChangeTokenRepository) {
        this.passwordChangeTokenRepository = passwordChangeTokenRepository;
    }

    @Override
    public PasswordChangeToken findToken(String token) {
        return passwordChangeTokenRepository.findByToken(token);
    }

    @Override
    public void save(PasswordChangeToken passwordChangeToken) {
        passwordChangeTokenRepository.save(passwordChangeToken);
    }
}
