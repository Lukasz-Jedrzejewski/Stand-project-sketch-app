package com.legion.standprojectapp.service;


import com.legion.standprojectapp.entity.Role;

public interface RoleService {
    void save(Role role);

    boolean existsByName(String name);
}