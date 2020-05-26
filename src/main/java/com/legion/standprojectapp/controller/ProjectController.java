package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.*;
import com.legion.standprojectapp.repository.BranchRepository;
import com.legion.standprojectapp.repository.FloorBoarRepository;
import com.legion.standprojectapp.repository.TypeOfBuildingRepository;
import com.legion.standprojectapp.service.ProjectService;
import org.hibernate.type.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private JavaMailSender javaMailSender;

    private ProjectService projectService;
    private TypeOfBuildingRepository typeOfBuildingRepository;
    private FloorBoarRepository floorBoarRepository;
    private BranchRepository branchRepository;

    public ProjectController(ProjectService projectService, TypeOfBuildingRepository typeOfBuildingRepository,
                             FloorBoarRepository floorBoarRepository, BranchRepository branchRepository) {
        this.projectService = projectService;
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

    @ModelAttribute("branches")
    public List<Branch> branches() {
        return branchRepository.findAll();
    }

    @GetMapping("/add")
    public String addProjectData(Model model) {
        model.addAttribute("project", new Project());
        return "addProjectData";
    }

    @GetMapping("/add/{id}")
    public String addProjectData(Model model, @PathVariable long id) {
        Optional<Project> byId = projectService.findProjectById(id);
        model.addAttribute("project", byId);
        return "addProjectData";
    }

    @PostMapping("/add")
    public String saveProjectData(@Valid @ModelAttribute Project project, BindingResult bindingResult, HttpSession session) throws IOException, MessagingException {
        if (bindingResult.hasErrors()) {
            return "addProjectData";
        }

        CurrentEvent currentEvent = (CurrentEvent) session.getAttribute("event");
        session.setAttribute("project", project);
        projectService.save(project);

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("generaljedrzejewski@gmail.com");
        helper.setSubject("New sketch");
        helper.setText("<h1>You have new project data!</h1>"+project.toHtml()+ " " + currentEvent.toHtml(), true);
        javaMailSender.send(msg);
        return "redirect:/home";
    }
}
