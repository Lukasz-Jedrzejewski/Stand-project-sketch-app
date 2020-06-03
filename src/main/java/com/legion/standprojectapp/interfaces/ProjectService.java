package com.legion.standprojectapp.interfaces;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Project;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    void save(Project project);
    Optional<Project> findProjectById(long id);
    void sendMail(Project project, CurrentEvent currentEvent, Branch branch) throws MessagingException;
    void changeBranchId(long id);
    void changeFloorBoardId(long id);
    void changeTypeOfBuildingId(long id);
    List<Project> findAllProjects();
    Project readSingleProject(long id);
    List<Project> findUserProjects(String companyMail);
    List<Project> findSorted();
}
