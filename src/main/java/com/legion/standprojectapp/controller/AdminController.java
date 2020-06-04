package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.*;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return "adminPanel";
    }

    @GetMapping("/branches")
    public String branchList(Model model) {
        model.addAttribute("branchList", branchService.findAll());
        return "branchList";
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
        return "adminBranchForm";
    }

    @GetMapping("/addBranch/{id}")
    public String editBranch(Model model, @PathVariable long id) {
        model.addAttribute("branch", branchService.getOne(id));
        return "adminBranchForm";
    }

    @PostMapping("/addBranch")
    public String branchPost(@Valid @ModelAttribute Branch branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminBranchForm";
        } else {
            branchService.save(branch);
        }
        return "redirect:/admin/branches";
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        model.addAttribute("eventList", currentEventService.findAll());
        return "eventList";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable long id) {
        currentEventService.delete(id);
        return "redirect:/admin/events";
    }

    @GetMapping("/addEvent")
    public String addEvent(Model model) {
        model.addAttribute("currentEvent", new CurrentEvent());
        return "adminEventForm";
    }

    @GetMapping("/addEvent/{id}")
    public String editEvent(Model model, @PathVariable long id) {
        model.addAttribute("currentEvent", currentEventService.getOne(id));
        return "adminEventForm";
    }

    @PostMapping("/addEvent")
    public String eventPost(@Valid @ModelAttribute CurrentEvent currentEvent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminEventForm";
        } else {
            currentEventService.save(currentEvent);
        }
        return "redirect:/admin/events";
    }

    @GetMapping("/floorBoards")
    public String floorBoardList(Model model) {
        model.addAttribute("floorBoardList", floorBoardService.findAll());
        return "floorBoardList";
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
        return "adminFloorBoardForm";
    }

    @GetMapping("/addFloorBoard/{id}")
    public String editFloorBoard(Model model, @PathVariable long id) {
        model.addAttribute("floorBoard", floorBoardService.getOne(id));
        return "adminFloorBoardForm";
    }

    @PostMapping("/addFloorBoard")
    public String floorBoardPost(@Valid @ModelAttribute FloorBoard floorBoard, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminFloorBoardForm";
        } else {
            floorBoardService.save(floorBoard);
        }
        return "redirect:/admin/floorBoards";
    }

    @GetMapping("/buildingTypes")
    public String typeOfBuildingList(Model model) {
        model.addAttribute("typeOfBuildingList", typeOfBuildingService.findAll());
        return "typeOfBuildingList";
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
        return "adminTypeOfBuildingForm";
    }

    @GetMapping("/addTypeOfBuilding/{id}")
    public String editTypeOfBuilding(Model model, @PathVariable long id) {
        model.addAttribute("typeOfBuilding", typeOfBuildingService.getOne(id));
        return "adminTypeOfBuildingForm";
    }

    @PostMapping("/addTypeOfBuilding")
    public String typeOfBuildingPost(@Valid @ModelAttribute TypeOfBuilding typeOfBuilding, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminTypeOfBuildingForm";
        } else {
            typeOfBuildingService.save(typeOfBuilding);
        }
        return "redirect:/admin/buildingTypes";
    }

    @GetMapping("/sketches")
    public String sketchList(Model model) {
        model.addAttribute("sketches", projectService.findAllProjects());
        return "sketchList";
    }

    @GetMapping("/showDetails/{id}")
    public String sketchDetails(Model model, @PathVariable long id) {
        model.addAttribute("sketch", projectService.readSingleProject(id));
        return "sketchDetails";
    }

    @GetMapping("/getUserMail")
    public String getUserMail() {
        return "searchByMail";
    }

    @GetMapping("/userProjects/")
    public String singleUserProjects(Model model, @RequestParam String companyMail) {
        boolean exist = userService.existByMail(companyMail);
        model.addAttribute("userProjects", projectService.findUserProjects(companyMail));
        if (exist) {
            return "userProjectList";
        } else {
            return "userNotExist";
        }
    }

    @GetMapping("sorted")
    public String getSorted(Model model) {
        model.addAttribute("sorted", projectService.findSorted());
        return "sortedList";
    }

    @GetMapping("/showFiles")
    public String show(Model model) {
        model.addAttribute("files", fileService.readFiles());
        return "filesList";
    }

    @GetMapping("/addProposition")
    public String getFiles(Model model) {
        model.addAttribute("files", new File());
        return "fileForm";
    }

    @PostMapping("/addProposition")
    public String addFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            fileService.save(file);
            
        }
        return "redirect:/admin/showFiles";
    }
}
