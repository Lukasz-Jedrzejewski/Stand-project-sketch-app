package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.EmailChangeToken;
import com.legion.standprojectapp.entity.PasswordChangeToken;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.model.EmailModel;
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
import javax.validation.groups.Default;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ProjectServiceImpl projectService;
    private final FileServiceImpl fileService;
    private final CompanyInfoServiceImpl companyInfoService;
    private final PasswordChangeTokenServiceImpl passwordChangeTokenService;
    private final MailServiceImpl mailService;
    private final EmailChangeTokenServiceImpl emailChangeTokenService;

    public UserController(UserServiceImpl userServiceImpl, ProjectServiceImpl projectService, FileServiceImpl fileService, CompanyInfoServiceImpl companyInfoService, PasswordChangeTokenServiceImpl passwordChangeTokenService, MailServiceImpl mailService, EmailChangeTokenServiceImpl emailChangeTokenService) {
        this.userServiceImpl = userServiceImpl;
        this.projectService = projectService;
        this.fileService = fileService;
        this.companyInfoService = companyInfoService;
        this.passwordChangeTokenService = passwordChangeTokenService;
        this.mailService = mailService;
        this.emailChangeTokenService = emailChangeTokenService;
    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal CurrentUser currentUser, Model model, HttpSession session) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        if (!userServiceImpl.checkRole(user.getId()))
            return "/user/userPanel";
        else
            return "redirect:/admin/adminPanel";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userServiceImpl.findById(id));
        model.addAttribute("email", new EmailModel());
        return "/user/editUserForm";
    }

    @PostMapping("/edit")
    public String editUser(@Validated(UserEditValidationGroup.class) @ModelAttribute User user,
                           BindingResult bindingResult,
                           @ModelAttribute("email") EmailModel emailModel,
                           HttpSession session) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "/user/editUserForm";
        }
        session.setAttribute("user", user);
        session.setAttribute("newEmail", emailModel.getEmail());
        EmailChangeToken emailChangeToken = new EmailChangeToken(user);
        emailChangeTokenService.save(emailChangeToken);
        mailService.sendEmailChangeToken(user.getCompanyMail(), emailChangeToken.getToken());
        return "/user/edit-verify";
    }

    @RequestMapping(value = "/edit-confirmation", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmEditData(@RequestParam("token") String emailChangeToken, HttpSession session) {
        EmailChangeToken token = emailChangeTokenService.findToken(emailChangeToken);
        if (token != null) {
            User userToEdit = (User) session.getAttribute("user");
            String newEmail = (String) session.getAttribute("newEmail");
            userServiceImpl.editUser(userToEdit, newEmail);
            return "/user/edit-successfully";
        } else {
            return "/user/edit-failed";
        }
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

    @GetMapping("/changePass/{id}")
    public String changePassword(Model model, @PathVariable long id) {
        model.addAttribute("user", userServiceImpl.findById(id));
        model.addAttribute("passwordModel", new PasswordModel());
        return "/user/changePass";
    }

    @PostMapping("/changePass")
    public String changePassword(@Validated(Default.class) @ModelAttribute("user") User user, BindingResult bindingResult,
                                 @ModelAttribute("passwordModel") PasswordModel passwordModel,
                                 HttpSession session) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "/user/changePass";
        }
        if (user.getPassword().equals(passwordModel.getConfirmPassword())) {
            PasswordChangeToken passwordChangeToken = new PasswordChangeToken(user);
            passwordChangeTokenService.save(passwordChangeToken);
            session.setAttribute("pass", passwordModel.getConfirmPassword());
            mailService.sendPasswordChangeToken(user.getCompanyMail(), passwordChangeToken.getToken());
            return "/user/change-verify";
        } else {
            return "/user/changePass";
        }
    }

    @RequestMapping(value = "/change-confirmation", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmRegister(@RequestParam("token") String passwordChangeToken, HttpSession session) {
        PasswordChangeToken token = passwordChangeTokenService.findToken(passwordChangeToken);
        if (token != null) {
            User user = userServiceImpl.findByCompanyMail(token.getUser().getCompanyMail());
            String pass = (String) session.getAttribute("pass");
            userServiceImpl.changePassword(user, pass);
            return "/user/change-successfully";
        } else {
            return "/user/change-failed";
        }
    }

}
