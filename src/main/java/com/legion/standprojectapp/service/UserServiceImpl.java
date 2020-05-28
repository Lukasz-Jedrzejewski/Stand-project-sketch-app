package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.interfaces.UserService;
import com.legion.standprojectapp.repository.RoleRepository;
import com.legion.standprojectapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkAdmin(){
        List<User> byAdmin = this.userRepository.findByAdmin();
        return byAdmin.size()>0;
    }

    @Override
    public void save(User user) {
        user.setAdmin(!checkAdmin());
        List<User> userList = this.userRepository.findAll();
        Role userRole;
        if (userList.size() == 0) {
            userRole = roleRepository.findByName("ROLE_ADMIN");
        } else {
            userRole = roleRepository.findByName("ROLE_USER");
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        this.userRepository.save(user);
    }


    @Override
    public User findByCompanyMail(String companyMail) {
        return this.userRepository.findByCompanyMail(companyMail);
    }

    public User findById(long id){
        return this.userRepository.getOne(id);
    }

}
