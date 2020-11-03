package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.*;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.service.serviceImpl.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private BranchServiceImpl branchService;
    private CurrentEventServiceImpl currentEventService;
    private FloorBoardServiceImpl floorBoardService;
    private TypeOfBuildingServiceImpl typeOfBuildingService;
    private ProjectServiceImpl projectService;
    private UserServiceImpl userService;
    private FileServiceImpl fileService;

    public AdminController(BranchServiceImpl branchService, CurrentEventServiceImpl currentEventService, FloorBoardServiceImpl floorBoardService, TypeOfBuildingServiceImpl typeOfBuildingService, ProjectServiceImpl projectService, UserServiceImpl userService, FileServiceImpl fileService) {
        this.branchService = branchService;
        this.currentEventService = currentEventService;
        this.floorBoardService = floorBoardService;
        this.typeOfBuildingService = typeOfBuildingService;
        this.projectService = projectService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/adminPanel")
    public String getAdminPanel(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        return "/admin/adminPanel";
    }

    @GetMapping("/branches")
    public String branchList(Model model) {
        model.addAttribute("branchList", branchService.findAll());
        return "/admin/branchList";
    }

    @GetMapping("/deleteBranch/{id}")
    public String deleteBranch(@PathVariable long id) {
        projectService.changeBranchId(id);
        branchService.delete(id);
        return "redirect:/admin/branches";
    }

    @GetMapping("/addBranch")
    public String addBranch(Model model) {
        model.addAttribute("branch", new Branch());
        return "/admin/adminBranchForm";
    }

    @GetMapping("/addBranch/{id}")
    public String editBranch(Model model, @PathVariable long id) {
        model.addAttribute("branch", branchService.getOne(id));
        return "/admin/adminBranchForm";
    }

    @PostMapping("/addBranch")
    public String branchPost(@Valid @ModelAttribute Branch branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/adminBranchForm";
        } else {
            branchService.save(branch);
        }
        return "redirect:/admin/branches";
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        model.addAttribute("eventList", currentEventService.findAll());
        return "/admin/eventList";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable long id) {
        currentEventService.delete(id);
        return "redirect:/admin/events";
    }

    @GetMapping("/addEvent")
    public String addEvent(Model model) {
        model.addAttribute("currentEvent", new CurrentEvent());
        return "/admin/adminEventForm";
    }

    @GetMapping("/addEvent/{id}")
    public String editEvent(Model model, @PathVariable long id) {
        model.addAttribute("currentEvent", currentEventService.getOne(id));
        return "/admin/adminEventForm";
    }

    @PostMapping("/addEvent")
    public String eventPost(@Valid @ModelAttribute CurrentEvent currentEvent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/adminEventForm";
        } else {
            currentEventService.save(currentEvent);
        }
        return "redirect:/admin/events";
    }

    @GetMapping("/floorBoards")
    public String floorBoardList(Model model) {
        model.addAttribute("floorBoardList", floorBoardService.findAll());
        return "/admin/floorBoardList";
    }

    @GetMapping("/deleteFloorBoard/{id}")
    public String deleteFloorBoard(@PathVariable long id) {
        projectService.changeFloorBoardId(id);
        floorBoardService.delete(id);
        return "redirect:/admin/floorBoards";
    }

    @GetMapping("/addFloorBoard")
    public String addFloorBoard(Model model) {
        model.addAttribute("floorBoard", new FloorBoard());
        return "/admin/adminFloorBoardForm";
    }

    @GetMapping("/addFloorBoard/{id}")
    public String editFloorBoard(Model model, @PathVariable long id) {
        model.addAttribute("floorBoard", floorBoardService.getOne(id));
        return "/admin/adminFloorBoardForm";
    }

    @PostMapping("/addFloorBoard")
    public String floorBoardPost(@Valid @ModelAttribute FloorBoard floorBoard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/adminFloorBoardForm";
        } else {
            floorBoardService.save(floorBoard);
        }
        return "redirect:/admin/floorBoards";
    }

    @GetMapping("/buildingTypes")
    public String typeOfBuildingList(Model model) {
        model.addAttribute("typeOfBuildingList", typeOfBuildingService.findAll());
        return "/admin/typeOfBuildingList";
    }

    @GetMapping("/deleteTypeOfBuilding/{id}")
    public String deleteTypeOfBuilding(@PathVariable long id) {
        projectService.changeTypeOfBuildingId(id);
        typeOfBuildingService.delete(id);
        return "redirect:/admin/buildingTypes";
    }

    @GetMapping("/addTypeOfBuilding")
    public String addTypeOfBuilding(Model model) {
        model.addAttribute("typeOfBuilding", new TypeOfBuilding());
        return "/admin/adminTypeOfBuildingForm";
    }

    @GetMapping("/addTypeOfBuilding/{id}")
    public String editTypeOfBuilding(Model model, @PathVariable long id) {
        model.addAttribute("typeOfBuilding", typeOfBuildingService.getOne(id));
        return "/admin/adminTypeOfBuildingForm";
    }

    @PostMapping("/addTypeOfBuilding")
    public String typeOfBuildingPost(@Valid @ModelAttribute TypeOfBuilding typeOfBuilding, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/adminTypeOfBuildingForm";
        } else {
            typeOfBuildingService.save(typeOfBuilding);
        }
        return "redirect:/admin/buildingTypes";
    }

    @GetMapping("/sketches")
    public String sketchList(Model model) {
        model.addAttribute("sketches", projectService.findAllProjects());
        return "/admin/sketchList";
    }

    @GetMapping("/showDetails/{id}")
    public String sketchDetails(Model model, @PathVariable long id) {
        model.addAttribute("sketch", projectService.readSingleProject(id));
        return "/admin/sketchDetails";
    }

    @GetMapping("/getUserMail")
    public String getUserMail() {
        return "/admin/searchByMail";
    }

    @GetMapping("/userProjects/")
    public String singleUserProjects(Model model, @RequestParam String companyMail) {
        boolean exist = userService.existByMail(companyMail);
        model.addAttribute("userProjects", projectService.findUserProjects(companyMail));
        if (exist) {
            return "/admin/userProjectList";
        } else {
            return "/admin/userNotExist";
        }
    }

    @GetMapping("/sorted")
    public String getSorted(Model model) {
        model.addAttribute("sorted", projectService.findSorted());
        return "/admin/sortedList";
    }

    @GetMapping("/showFiles")
    public String show(Model model) {
        model.addAttribute("files", fileService.readFiles());
        return "/admin/filesList";
    }

    @GetMapping("/showFiles/{id}")
    public String showById(Model model, @PathVariable long id) {
        model.addAttribute("files", fileService.readAllByProjectId(id));
        return "/admin/filesList";
    }

    @GetMapping("/addProposition/{id}")
    public String getFiles(Model model, @PathVariable long id, HttpSession session) {
        File file = new File();
        Project project = projectService.readSingleProject(id);
        session.setAttribute("pr", project);
        model.addAttribute("files", file);
        return "/admin/fileForm";
    }

    @PostMapping("/addProposition")
    public String addFiles(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        Project project = (Project) session.getAttribute("pr");
        for (MultipartFile file : files) {
            fileService.save(file, project);
            
        }
        return "redirect:/admin/showFiles";
    }

    @GetMapping("/menage")
    public String menageContentAction() {
        return "/admin/menageContent";
    }
}
