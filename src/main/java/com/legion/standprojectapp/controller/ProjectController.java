package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.*;
import com.legion.standprojectapp.repository.BranchRepository;
import com.legion.standprojectapp.repository.FloorBoarRepository;
import com.legion.standprojectapp.repository.TypeOfBuildingRepository;
import com.legion.standprojectapp.service.serviceImpl.ProjectServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectServiceImpl projectServiceImpl;
    private TypeOfBuildingRepository typeOfBuildingRepository;
    private FloorBoarRepository floorBoarRepository;
    private BranchRepository branchRepository;

    public ProjectController(ProjectServiceImpl projectServiceImpl, TypeOfBuildingRepository typeOfBuildingRepository,
                             FloorBoarRepository floorBoarRepository, BranchRepository branchRepository) {
        this.projectServiceImpl = projectServiceImpl;
        this.typeOfBuildingRepository = typeOfBuildingRepository;
        this.floorBoarRepository = floorBoarRepository;
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

    @GetMapping("/add")
    public String addProjectData(Model model) {
        model.addAttribute("project", new Project());
        return "addProjectData";
    }

    @GetMapping("/add/{id}")
    public String addProjectData(Model model, @PathVariable long id) {
        Optional<Project> byId = projectServiceImpl.findProjectById(id);
        model.addAttribute("project", byId);
        return "addProjectData";
    }



    @PostMapping("/add")
    public String saveProjectData(@Valid @ModelAttribute Project project, BindingResult bindingResult, HttpSession session) throws MessagingException {
        Branch branch = (Branch) session.getAttribute("branch");
        CurrentEvent currentEvent = (CurrentEvent) session.getAttribute("currentEvent");
        User user = (User) session.getAttribute("user");
        session.setAttribute("project", project);
        project.setBranch(branch);
        project.setCompanyName(user.getCompanyName());
        project.setCompanyMail(user.getCompanyMail());
        if (bindingResult.hasErrors()) {
            return "addProjectData";
        }

        projectServiceImpl.save(project);

        projectServiceImpl.sendMail(project, currentEvent, branch);

        return "redirect:/user/about";
    }
}
