package com.legion.standprojectapp.fixture;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.service.BranchServiceImpl;
import com.legion.standprojectapp.service.RoleServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class InitData {
    private RoleServiceImpl roleService;
    private BranchServiceImpl branchService;

    public InitData(RoleServiceImpl roleService, BranchServiceImpl branchService) {
        this.roleService = roleService;
        this.branchService = branchService;
    }

    public void initRoles() {
        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        roleService.save(admin);

        Role user = new Role();
        user.setName("ROLE_USER");
        roleService.save(user);
    }

    public void initBranches() {
        Branch wood = new Branch();
        wood.setName("drewno/meble");
        branchService.save(wood);

        Branch cooking = new Branch();
        cooking.setName("gastronomia");
        branchService.save(cooking);

        Branch medical = new Branch();
        medical.setName("medyczna");
        branchService.save(medical);
    }
}
