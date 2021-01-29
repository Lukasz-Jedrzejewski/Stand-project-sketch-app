package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.FloorBoard;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.entity.TypeOfBuilding;
import com.legion.standprojectapp.repository.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @AfterEach
    public void clear() {
        projectRepository.deleteAll();
    }

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
    @DisplayName("test findByProjectId")
    void findProjectById() {
        projectService.save(project1);
        assertAll(
                () -> {
                    assertNotNull(projectService.findProjectById(project1.getId()), "This element should be saved");
                },
                () -> {
                    assertEquals(Optional.of(project1).toString(), projectService.findProjectById(project1.getId()).toString(),
                            "Objects should be the same");
                }
        );

    }

    @Test
    @DisplayName("test changeBranchId")
    void changeBranchId() {
        projectService.save(project1);
        Optional<Project> projectById = projectService.findProjectById(project1.getId());
        assertAll(
                () -> {
                    assertEquals(Optional.of(project1.getBranch().getId()), projectById.map(Project::getBranch).map(Branch::getId),
                            "Branch id should be the same");
                },
                () -> {
                    projectService.changeBranchId(project1.getBranch().getId());
                    Optional<Project> actual = projectService.findProjectById(project1.getId());
                    assertEquals(Optional.empty(), actual.map(Project::getBranch).map(Branch::getId),
                            "Branch id should be null");
                }
        );
    }

    @Test
    @DisplayName("test changeFloorBoardId")
    void changeFloorBoardId() {
        projectService.save(project1);
        Optional<Project> projectById = projectService.findProjectById(project1.getId());
        assertAll(
                () -> {
                    assertEquals(Optional.of(project1.getFloorBoard().getId()),
                            projectById.map(Project::getFloorBoard).map(FloorBoard::getId),
                            "Floorboard id should be the same");
                },
                () -> {
                    projectService.changeFloorBoardId(project1.getFloorBoard().getId());
                    Optional<Project> actual = projectService.findProjectById(project1.getId());
                    assertEquals(Optional.empty(), actual.map(Project::getFloorBoard).map(FloorBoard::getId),
                            "Floorboard id should be null");
                }
        );
    }

    @Test
    @DisplayName("test changeTypeOfBuildingId")
    void changeTypeOfBuildingId() {
        projectService.save(project1);
        Optional<Project> projectById = projectService.findProjectById(project1.getId());
        assertAll(
                () -> {
                    assertEquals(Optional.of(project1.getTypeOfBuilding().getId()),
                            projectById.map(Project::getTypeOfBuilding).map(TypeOfBuilding::getId),
                            "Type of building id should be the same");
                },
                () -> {
                    projectService.changeTypeOfBuildingId(project1.getTypeOfBuilding().getId());
                    Optional<Project> actual = projectService.findProjectById(project1.getId());
                    assertEquals(Optional.empty(), actual.map(Project::getTypeOfBuilding).map(TypeOfBuilding::getId),
                            "Type of building id should be null");
                }
        );
    }

    @Test
    @DisplayName("test findAllProjects")
    void findAllProjects() {
        assertAll(
                () -> {
                    assertTrue(projectService.findAllProjects().isEmpty(), "List should be empty");
                },
                () -> {
                    projectService.save(project1);
                    assertFalse(projectService.findAllProjects().isEmpty(), "One element should be saved");
                    assertEquals(1, projectService.findAllProjects().size(), "One element should be saved");
                }
        );
    }

    @Test
    @DisplayName("test readSingleProject")
    @Transactional
    void readSingleProject() {
        projectService.save(project1);
        assertNotNull(projectService.readSingleProject(project1.getId()), "This element should be saved");
        assertEquals(project1.toString(), projectService.readSingleProject(project1.getId()).toString(),
                "elements should be same");
    }

    @Test
    @DisplayName("test findUserProjects")
    @Transactional
    void findUserProjects() {
        Project project2 = new Project("4", "4", typeOfBuilding, false, true,
                "3", false, false, floorBoard, 2, branch, "bbb",
                "bbb@mail.com", LocalDate.now(), null);
        Project project3 = new Project("3", "3", typeOfBuilding, false, true,
                "3", true, false, floorBoard, 2, branch, "aaa",
                "aaa@mail.com", LocalDate.now(), null);
        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);
        assertAll(
                () -> {
                    assertEquals(3, projectService.findAllProjects().size(),
                            "Three elements should be saved");
                },
                () -> {
                    assertEquals(2, projectService.findUserProjects(project1.getCompanyMail()).size(),
                            "Two elements should be found");
                    assertEquals(1, projectService.findUserProjects(project2.getCompanyMail()).size(),
                            "One elements should be found");
                }
        );
    }

    @Test
    @DisplayName("test findSorted")
    @Transactional
    void findSorted() {
        Project project2 = new Project("4", "4", typeOfBuilding, false, true,
                "3", false, false, floorBoard, 2, branch, "bbb",
                "bbb@mail.com", LocalDate.now(), null);
        Project project3 = new Project("3", "3", typeOfBuilding, false, true,
                "3", true, false, floorBoard, 2, branch, "aaa",
                "aaa@mail.com", LocalDate.now(), null);
        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);
        Optional<Project> project2ById = projectService.findProjectById(project2.getId());
        Optional<Project> project3ById = projectService.findProjectById(project3.getId());
        if (project2ById.isPresent()) {
            project2ById.get().setCreated(LocalDate.parse("2020-11-06"));
            projectService.save(project2ById.get());
        }
        if (project3ById.isPresent()) {
            project3ById.get().setCreated(LocalDate.parse("2020-11-22"));
            projectService.save(project3ById.get());
        }
        assertAll(
                () -> {
                    assertEquals(3, projectService.findAllProjects().size(),
                            "Three elements should be saved");
                },
                () -> {
                    List<Project> sorted = projectService.findSorted();
                    assertEquals(project1.toString(), sorted.get(0).toString(),
                            "Project1 should be first on the list");
                    assertEquals(project3.toString(), sorted.get(1).toString(),
                            "Project3 should be second on the list");
                    assertEquals(project2.toString(), sorted.get(2).toString(),
                            "Project2 should be last on the list");
                }
        );
    }
}