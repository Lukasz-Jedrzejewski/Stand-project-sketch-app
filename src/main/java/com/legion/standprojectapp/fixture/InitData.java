package com.legion.standprojectapp.fixture;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.service.BranchServiceImpl;
import com.legion.standprojectapp.service.CurrentEventServiceImpl;
import com.legion.standprojectapp.service.RoleServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class InitData {
    private RoleServiceImpl roleService;
    private BranchServiceImpl branchService;
    private CurrentEventServiceImpl currentEventService;

    public InitData(RoleServiceImpl roleService, BranchServiceImpl branchService, CurrentEventServiceImpl currentEventService) {
        this.roleService = roleService;
        this.branchService = branchService;
        this.currentEventService = currentEventService;
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

    public void initEvents() {
        CurrentEvent event1 = new CurrentEvent();
        event1.setName("Drema");
        event1.setCity("Poznań");
        currentEventService.save(event1);

        CurrentEvent event2 = new CurrentEvent();
        event2.setName("Euro-gastro");
        event2.setCity("Warszawa");
        currentEventService.save(event2);

        CurrentEvent event3 = new CurrentEvent();
        event3.setName("Medica");
        event3.setCity("Dusseldorf");
        currentEventService.save(event3);
    }


}
