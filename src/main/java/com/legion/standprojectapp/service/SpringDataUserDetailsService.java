package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Role;
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
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) {
        User user = userService.findByCompanyMail(mail);
        if (user == null) {throw new UsernameNotFoundException(mail); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles =  user.getRoles();
        roles.forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), grantedAuthorities);

//        Change to own implementation
        return (UserDetails) new CurrentUser(user.getCompanyMail(), user.getPassword(), grantedAuthorities, user);
    }
}
//
//    @Override
//    public UserDetails loadUserByUsername(String companyMail) throws UsernameNotFoundException {
//        User user = userService.findByCompanyMail(companyMail);
//        if (user == null) {throw new UsernameNotFoundException(companyMail); }
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        user.getRoles().forEach(r ->
//                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
//        return (UserDetails) new CurrentUser(user.getCompanyMail(),user.getPassword(),
//                grantedAuthorities, user);
//
//
//    }


