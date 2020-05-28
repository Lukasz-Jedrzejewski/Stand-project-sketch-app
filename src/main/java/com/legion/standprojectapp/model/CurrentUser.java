package com.legion.standprojectapp.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final com.legion.standprojectapp.entity.User user;
    public CurrentUser(String companyMail, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.legion.standprojectapp.entity.User user) {
        super(companyMail, password, authorities);
        this.user = user;
    }
    public com.legion.standprojectapp.entity.User getUser() {return user;}


}
