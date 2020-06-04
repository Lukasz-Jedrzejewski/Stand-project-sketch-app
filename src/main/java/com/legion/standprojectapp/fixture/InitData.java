package com.legion.standprojectapp.fixture;

import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.service.RoleServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class InitData {
    private RoleServiceImpl roleService;

    public InitData(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    public void initRoles() {
        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        roleService.save(admin);

        Role user = new Role();
        user.setName("ROLE_USER");
        roleService.save(user);
    }
}
