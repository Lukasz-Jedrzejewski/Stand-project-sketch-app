package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.EmailChangeToken;

public interface EmailChangeTokenService {
    EmailChangeToken findToken(String token);
    void save(EmailChangeToken emailChangeToken);
}
