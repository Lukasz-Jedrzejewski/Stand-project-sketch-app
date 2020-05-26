package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void save(Project project) {
        this.projectRepository.save(project);
    }

    public Optional<Project> findProjectById(long id){
        return this.projectRepository.findById(id);
    }
}
