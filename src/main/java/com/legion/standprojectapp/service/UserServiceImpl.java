package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.interfaces.UserService;
import com.legion.standprojectapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkAdmin(){
        List<User> byAdmin = this.userRepository.findByAdmin();
        return byAdmin.size()>0;
    }

    @Override
    public void save(User user) {
        user.setAdmin(!checkAdmin());
        this.userRepository.save(user);
    }
}
