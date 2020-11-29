package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.PasswordChangeToken;

public interface PasswordChangeTokenService {
    PasswordChangeToken findToken(String token);
    void save(PasswordChangeToken passwordChangeToken);
}
