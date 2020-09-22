package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.VerificationToken;

public interface VerificarionTokenService {
    VerificationToken findToken(String token);
    void save(VerificationToken verificationToken);
    void deleteByUserId(long id);
}
