package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.FloorBoard;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.entity.TypeOfBuilding;
import com.legion.standprojectapp.repository.BranchRepository;
import com.legion.standprojectapp.repository.FloorBoarRepository;
import com.legion.standprojectapp.repository.ProjectRepository;
import com.legion.standprojectapp.repository.TypeOfBuildingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private TypeOfBuildingRepository typeOfBuildingRepository;
    private FloorBoarRepository floorBoarRepository;
    private ProjectRepository projectRepository;
    private BranchRepository branchRepository;

    public ProjectController(TypeOfBuildingRepository typeOfBuildingRepository, FloorBoarRepository floorBoarRepository, ProjectRepository projectRepository, BranchRepository branchRepository) {
        this.typeOfBuildingRepository = typeOfBuildingRepository;
        this.floorBoarRepository = floorBoarRepository;
        this.projectRepository = projectRepository;
        this.branchRepository = branchRepository;
    }

    @ModelAttribute("typesOfBuilding")
    public List<TypeOfBuilding> typesOfBuilding() {
        return typeOfBuildingRepository.findAll();
    }

    @ModelAttribute("typesOfFloorBoard")
    public List<FloorBoard> typesOfFloorBoard() {
        return floorBoarRepository.findAll();
    }

    @ModelAttribute("branches")
    public List<Branch> branches() {
        return branchRepository.findAll();
    }


    @GetMapping("/add")
    public String addProjectData(Model model) {
        model.addAttribute("projectData", new Project());
        return "addProjectData";
    }

    @GetMapping("/add/{id}")
    public String addProjectData(Model model, @PathVariable long id) {
        Optional<Project> byId = projectRepository.findById(id);
        model.addAttribute("projectData", byId);
        return "addProjectData";
    }

    @PostMapping("/add")
    public String saveProjectData(@Valid @ModelAttribute Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addProjectData";
        }
        if (project.getId() != null) {
            projectRepository.save(project);
        } else {
            projectRepository.save(project);
        }
        return "redirect:/home";
    }
}
