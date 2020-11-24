package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.service.serviceImpl.*;
import com.legion.standprojectapp.validation.groups.UserEditValidationGroup;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;
    private ProjectServiceImpl projectService;
    private FileServiceImpl fileService;
    private CompanyInfoServiceImpl companyInfoService;

    public UserController(UserServiceImpl userServiceImpl, ProjectServiceImpl projectService, FileServiceImpl fileService, CompanyInfoServiceImpl companyInfoService) {
        this.userServiceImpl = userServiceImpl;
        this.projectService = projectService;
        this.fileService = fileService;
        this.companyInfoService = companyInfoService;
    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal CurrentUser currentUser, Model model, HttpSession session) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        model.addAttribute("logo", companyInfoService.getOne(1));
        if (!userServiceImpl.checkRole(user.getId()))
            return "/user/userPanel";
        else
            return "redirect:/admin/adminPanel";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userServiceImpl.findById(id));
        return "/user/editUserForm";
    }

    @PostMapping("/edit")
    public String editUser(@Validated(UserEditValidationGroup.class) @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/editUserForm";
        }
        userServiceImpl.save(user);
        return "redirect:/user/about";
    }

    @GetMapping("/mySketches")
    public String mySketches(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String companyMail = user.getCompanyMail();
        model.addAttribute("userProjects", projectService.findUserProjects(companyMail));
        return "/user/yourProjectList";
    }

    @GetMapping("/showDetails/{id}")
    public String sketchDetails(Model model, @PathVariable long id) {
        model.addAttribute("sketch", projectService.readSingleProject(id));
        return "/user/yourSketchDetails";
    }

    @GetMapping("/showFiles/{id}")
    public String showById(Model model, @PathVariable long id) {
        model.addAttribute("files", fileService.readAllByProjectId(id));
        return "/user/yourFilesList";
    }

    @GetMapping("/changePass")
    public String changePassword(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("userPass", currentUser.getUser());
        return "/user/changePass";
    }

    @PostMapping("/changePass")
    public String changePassword(@Valid @AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute User userPass, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/changePass";
        }
        userServiceImpl.changePassword(currentUser, userPass);
        return "redirect:/user/about";
    }


}
