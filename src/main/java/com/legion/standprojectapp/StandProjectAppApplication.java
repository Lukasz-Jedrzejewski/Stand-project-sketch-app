package com.legion.standprojectapp;

import com.legion.standprojectapp.fixture.InitData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StandProjectAppApplication implements CommandLineRunner {

    private final InitData initData;

    public StandProjectAppApplication(InitData initData) {
        this.initData = initData;
    }

    public static void main(String[] args) {
        SpringApplication.run(StandProjectAppApplication.class, args);
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
