package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.service.ProjectService;
import com.legion.standprojectapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void save(Project project) {
        this.projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProjectById(long id){
        return this.projectRepository.findById(id);
    }

    @Override
    public void changeBranchId(long id) {
        projectRepository.setBranchIdToNull(id);
    }

    @Override
    public void changeFloorBoardId(long id) {
        projectRepository.setFloorBoardIdToNull(id);
    }

    @Override
    public void changeTypeOfBuildingId(long id) {
        projectRepository.setTypeOfBuildingIdToNull(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project readSingleProject(long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public List<Project> findUserProjects(String companyMail) {
        return projectRepository.findAllByCompanyMailLike(companyMail);
    }

    @Override
    public List<Project> findSorted() {
        return projectRepository.findAllByOrderByCreatedDesc();
    }


}
