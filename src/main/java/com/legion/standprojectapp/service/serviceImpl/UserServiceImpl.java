package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.service.UserService;
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
    public boolean checkAdmin() {
        List<User> byAdmin = this.userRepository.findByAdmin();
        return byAdmin.size() > 0;
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
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        this.userRepository.save(user);
    }

    @Override
    public void editUser(User user) {
        User userDb = userRepository.getOne(user.getId());
        user.setEnabled(userDb.isEnabled());
        user.setRoles(userDb.getRoles());
        user.setPassword(userDb.getPassword());
        userRepository.save(user);
    }

    @Override
    public void editUser(User user, String email) {
        User userFromDB = findById(user.getId());
        userFromDB.setCompanyMail(email);
        userFromDB.setCompanyName(user.getCompanyName());
        userRepository.save(userFromDB);
    }


    @Override
    public User findByCompanyMail(String companyMail) {
        return this.userRepository.findByCompanyMail(companyMail);
    }

    public User findById(long id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public boolean checkRole(long id) {
        User one = this.userRepository.getOne(id);
        return one.isAdmin();
    }

    @Override
    public boolean existByMail(String companyMail) {
        return userRepository.existsUserByCompanyMail(companyMail);
    }

    @Override
    public void changePassword(User user, String password) {
        User userFromDB = userRepository.findByCompanyMail(user.getCompanyMail());
        userFromDB.setId(user.getId());
        userFromDB.setPassword(passwordEncoder.encode(password));
        userFromDB.setCompanyName(user.getCompanyName());
        userFromDB.setCompanyMail(user.getCompanyMail());
        userFromDB.setAdmin(user.isAdmin());
        userFromDB.setEnabled(user.isEnabled());
        userFromDB.setRoles(user.getRoles());
        userRepository.save(userFromDB);
    }

    @Override
    public void resetPassword(long id, String password) {
        User userFromDB = userRepository.getOne(id);
        userFromDB.setPassword(passwordEncoder.encode(password));
        userRepository.save(userFromDB);
    }

    @Override
    public User findAdmin() {
        return userRepository.findByAdminTrue();
    }
}
