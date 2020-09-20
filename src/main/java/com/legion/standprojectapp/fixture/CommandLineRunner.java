package com.legion.standprojectapp.fixture;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final InitData initData;

    public CommandLineRunner(InitData initData) {
        this.initData = initData;
    }

    @Override
    public void run(String... args) throws Exception {
        initData.initRoles();
        initData.initBranches();
        initData.initEvents();
        initData.initFloorBoard();
        initData.initBuildingTypes();
    }
}
