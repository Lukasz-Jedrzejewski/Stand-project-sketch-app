package com.legion.standprojectapp.model;

import com.legion.standprojectapp.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CurrentUser extends User {
    private final User user;
    public CurrentUser(String companyMail, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       User user) {
        super(companyMail, password, authorities);
        this.user = user;
    }
    public User getUser() {return user;}
}
