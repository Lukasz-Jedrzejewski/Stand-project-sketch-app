package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.EmailChangeToken;
import com.legion.standprojectapp.repository.EmailChangeTokenRepository;
import com.legion.standprojectapp.service.EmailChangeTokenService;
import org.springframework.stereotype.Service;

@Service
public class EmailChangeTokenServiceImpl implements EmailChangeTokenService {

    private final EmailChangeTokenRepository emailChangeTokenRepository;

    public EmailChangeTokenServiceImpl(EmailChangeTokenRepository emailChangeTokenRepository) {
        this.emailChangeTokenRepository = emailChangeTokenRepository;
    }

    @Override
    public EmailChangeToken findToken(String token) {
        return emailChangeTokenRepository.findByToken(token);
    }

    @Override
    public void save(EmailChangeToken emailChangeToken) {
        emailChangeTokenRepository.save(emailChangeToken);
    }
}
