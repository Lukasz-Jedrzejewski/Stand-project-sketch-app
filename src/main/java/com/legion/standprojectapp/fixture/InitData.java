package com.legion.standprojectapp.fixture;

import com.legion.standprojectapp.entity.*;
import com.legion.standprojectapp.service.serviceImpl.*;
import org.springframework.stereotype.Service;

@Service
public class InitData {
    private RoleServiceImpl roleService;
    private BranchServiceImpl branchService;
    private CurrentEventServiceImpl currentEventService;
    private FloorBoardServiceImpl floorBoardService;
    private TypeOfBuildingServiceImpl typeOfBuildingService;

    public InitData(RoleServiceImpl roleService, BranchServiceImpl branchService, CurrentEventServiceImpl currentEventService, FloorBoardServiceImpl floorBoardService, TypeOfBuildingServiceImpl typeOfBuildingService) {
        this.roleService = roleService;
        this.branchService = branchService;
        this.currentEventService = currentEventService;
        this.floorBoardService = floorBoardService;
        this.typeOfBuildingService = typeOfBuildingService;
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

    public void initFloorBoard() {
        FloorBoard floor1 = new FloorBoard();
        floor1.setName("wykładzina");
        floorBoardService.save(floor1);

        FloorBoard floor2 = new FloorBoard();
        floor2.setName("panele");
        floorBoardService.save(floor2);

        FloorBoard floor3 = new FloorBoard();
        floor3.setName("laminat");
        floorBoardService.save(floor3);
    }

    public void initBuildingTypes() {
        TypeOfBuilding type1 = new TypeOfBuilding();
        type1.setName("baner");
        typeOfBuildingService.save(type1);

        TypeOfBuilding type2 = new TypeOfBuilding();
        type2.setName("drewno");
        typeOfBuildingService.save(type2);

        TypeOfBuilding type3 = new TypeOfBuilding();
        type3.setName("system");
        typeOfBuildingService.save(type3);
    }
}
