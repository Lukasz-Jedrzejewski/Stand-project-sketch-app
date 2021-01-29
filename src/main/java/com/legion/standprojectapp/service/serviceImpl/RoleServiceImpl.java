package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.service.RoleService;
import com.legion.standprojectapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        boolean existRole = existsByName(role.getName());
        if (!existRole) {
            this.roleRepository.save(role);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsRoleByName(name);
    }
}