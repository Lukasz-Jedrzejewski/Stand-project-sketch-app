package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.PasswordResetToken;

public interface PasswordResetTokenService {
    PasswordResetToken findToken(String token);
    void save(PasswordResetToken passwordResetToken);
}
