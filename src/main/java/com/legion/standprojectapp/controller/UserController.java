package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.entity.VerificationToken;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.model.PasswordModel;
import com.legion.standprojectapp.service.serviceImpl.*;
import com.legion.standprojectapp.validation.groups.UserEditValidationGroup;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.groups.Default;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;
    private ProjectServiceImpl projectService;
    private FileServiceImpl fileService;
    private VerificationTokenServiceImpl verificationTokenService;
    private MailServiceImpl mailService;

    public UserController(UserServiceImpl userServiceImpl, ProjectServiceImpl projectService, FileServiceImpl fileService, VerificationTokenServiceImpl verificationTokenService, MailServiceImpl mailService) {
        this.userServiceImpl = userServiceImpl;
        this.projectService = projectService;
        this.fileService = fileService;
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
    }

    @GetMapping("/register")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("passwordModel", new PasswordModel());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Validated(Default.class) @ModelAttribute("user") User user,
                          BindingResult bindingResult,
                          @ModelAttribute("passwordModel") PasswordModel passwordModel) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "register";
        }
            if (user.getPassword().equals(passwordModel.getConfirmPassword())) {
                user.setEnabled(false);
                userServiceImpl.save(user);
                VerificationToken verificationToken = new VerificationToken(user);
                verificationTokenService.save(verificationToken);
                mailService.sendVerificationToken(user.getCompanyMail(), verificationToken.getToken());
            } else {
                return "register";
            }
        return "register-verify";

    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal CurrentUser currentUser, Model model, HttpSession session) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        if (!userServiceImpl.checkRole(user.getId()))
            return "userPanel";
        else
            return "redirect:/admin/adminPanel";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userServiceImpl.findById(id));
        return "editUserForm";
    }

    @PostMapping("/edit")
    public String editUser(@Validated(UserEditValidationGroup.class) @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editUserForm";
        }
        userServiceImpl.save(user);
        return "redirect:/user/about";
    }

    @GetMapping("/mySketches")
    public String mySketches(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String companyMail = user.getCompanyMail();
        model.addAttribute("userProjects", projectService.findUserProjects(companyMail));
        return "yourProjectList";
    }

    @GetMapping("/showDetails/{id}")
    public String sketchDetails(Model model, @PathVariable long id) {
        model.addAttribute("sketch", projectService.readSingleProject(id));
        return "yourSketchDetails";
    }

    @GetMapping("/showFiles/{id}")
    public String showById(Model model, @PathVariable long id) {
        model.addAttribute("files", fileService.readAllByProjectId(id));
        return "yourFilesList";
    }

    @GetMapping("/changePass")
    public String changePassword(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("userPass", currentUser.getUser());
        return "changePass";
    }

    @PostMapping("/changePass")
    public String changePassword(@Valid @AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute User userPass, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "changePass";
        }
        userServiceImpl.changePassword(currentUser, userPass);
        return "redirect:/user/about";
    }


}
