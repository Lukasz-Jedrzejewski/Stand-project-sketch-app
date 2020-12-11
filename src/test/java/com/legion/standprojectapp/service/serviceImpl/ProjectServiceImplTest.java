package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.FloorBoard;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.entity.TypeOfBuilding;
import com.legion.standprojectapp.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandProjectAppApplication.class)
class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private ProjectRepository projectRepository;

    TypeOfBuilding typeOfBuilding = new TypeOfBuilding("test");
    FloorBoard floorBoard = new FloorBoard("test");
    Branch branch = new Branch("test");
    Project project1 = new Project("3", "3", typeOfBuilding, false, false,
            "3", false, false, floorBoard, 2, branch, "aaa",
            "aaa@mail.com", LocalDate.now(), null);

    @Test
    @DisplayName("test save")
    void save() {
        Project project = new Project();
        assertEquals(0, projectRepository.findAll().size(), "Nothing should be saved");
        assertAll(
                () -> {
                    Exception exception = assertThrows(ConstraintViolationException.class,
                            () -> projectService.save(project));
                    String expectedMessage = "constraint violations";
                    assertTrue(exception.getMessage().contains(expectedMessage),
                            "Exception should be thrown, " +
                                    "because one or more fields do not meet the validation conditions");
                    assertEquals(0, projectService.findAllProjects().size(), "One element should be saved");
                },
                () -> {
                    projectService.save(project1);
                    assertFalse(projectService.findAllProjects().isEmpty(), "One element should be saved");
                    assertEquals(1, projectRepository.findAll().size(), "One element should be saved");
                }
        );
    }

    @Test
    void findProjectById() {
    }

    @Test
    void changeBranchId() {
    }

    @Test
    void changeFloorBoardId() {
    }

    @Test
    void changeTypeOfBuildingId() {
    }

    @Test
    void findAllProjects() {
    }

    @Test
    void readSingleProject() {
    }

    @Test
    void findUserProjects() {
    }

    @Test
    void findSorted() {
    }
}