package com.legion.standprojectapp.service;

import com.legion.standprojectapp.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
