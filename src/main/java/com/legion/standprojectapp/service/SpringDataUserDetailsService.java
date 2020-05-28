package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.interfaces.UserService;
import com.legion.standprojectapp.model.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SpringDataUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String companyMail) throws UsernameNotFoundException {
        User user = userService.findByCompanyMail(companyMail);
        if (user == null) {throw new UsernameNotFoundException(companyMail); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return (UserDetails) new CurrentUser(user.getCompanyMail(),user.getPassword(),
                grantedAuthorities, user);


    }
    }

