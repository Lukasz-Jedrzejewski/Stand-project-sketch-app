package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.service.UserService;
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
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String companyMail) {
        User user = userService.findByCompanyMail(companyMail);
        if (user == null) {throw new UsernameNotFoundException(companyMail); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles =  user.getRoles();
        roles.forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CurrentUser(user.getCompanyMail(), user.getPassword(), grantedAuthorities, user);
    }
}



