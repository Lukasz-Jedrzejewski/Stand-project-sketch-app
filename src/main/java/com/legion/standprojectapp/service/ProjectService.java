package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void save(Project project);
    Optional<Project> findProjectById(long id);
    void changeBranchId(long id);
    void changeFloorBoardId(long id);
    void changeTypeOfBuildingId(long id);
    List<Project> findAllProjects();
    Project readSingleProject(long id);
    List<Project> findUserProjects(String companyMail);
    List<Project> findSorted();
}
