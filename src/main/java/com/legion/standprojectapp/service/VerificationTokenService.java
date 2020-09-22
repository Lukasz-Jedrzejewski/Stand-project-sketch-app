package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findToken(String token);
    void save(VerificationToken verificationToken);
}
