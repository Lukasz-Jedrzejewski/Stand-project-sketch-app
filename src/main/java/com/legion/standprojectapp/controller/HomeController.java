package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.entity.VerificationToken;
import com.legion.standprojectapp.model.PasswordModel;
import com.legion.standprojectapp.service.serviceImpl.MailServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.UserServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.VerificationTokenServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.groups.Default;

@Controller
public class HomeController {

    private final UserServiceImpl userService;
    private final VerificationTokenServiceImpl verificationTokenService;
    private final MailServiceImpl mailService;

    public HomeController(UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService, MailServiceImpl mailService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
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
            userService.save(user);
            VerificationToken verificationToken = new VerificationToken(user);
            verificationTokenService.save(verificationToken);
            mailService.sendVerificationToken(user.getCompanyMail(), verificationToken.getToken());
        } else {
            return "register";
        }
        return "register-verify";
    }

    @RequestMapping(value = "/confirm-register", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmRegister(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findToken(verificationToken);
        if (token != null) {
            User user = userService.findByCompanyMail(token.getUser().getCompanyMail());
            user.setEnabled(true);
            userService.editUser(user);
            return "/register-successfully";
        } else {
            return "/register-failed";
        }
    }
}
