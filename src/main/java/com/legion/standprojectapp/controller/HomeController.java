package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.PasswordResetToken;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.entity.VerificationToken;
import com.legion.standprojectapp.model.EmailModel;
import com.legion.standprojectapp.model.PasswordModel;
import com.legion.standprojectapp.service.serviceImpl.MailServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.PasswordResetTokenServiceImpl;
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
    private final PasswordResetTokenServiceImpl passwordResetTokenService;

    public HomeController(UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService, MailServiceImpl mailService, PasswordResetTokenServiceImpl passwordResetTokenService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
        this.passwordResetTokenService = passwordResetTokenService;
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

    @GetMapping("/reset-password")
    public String resetPassword(Model model){
        model.addAttribute("emailModel", new EmailModel());
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String postResetPassword(@ModelAttribute EmailModel emailModel) throws MessagingException {
        String email = emailModel.getEmail();
        PasswordResetToken passwordResetToken = new PasswordResetToken(userService.findByCompanyMail(email));
        passwordResetTokenService.save(passwordResetToken);
        mailService.sendPasswordResetToken(email, passwordResetToken.getToken());
        return "reset-password-info";
    }

    @RequestMapping(value = "/reset-confirmation", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmPasswordReset(@RequestParam("token") String passwordResetToken, Model model) {
        PasswordResetToken token = passwordResetTokenService.findToken(passwordResetToken);
        if (token != null) {
            User user = userService.findByCompanyMail(token.getUser().getCompanyMail());
            model.addAttribute("user", user);
            model.addAttribute("passwordModel", new PasswordModel());
            return "set-password";
        } else {
            return "set-password-failed";
        }
    }

    @PostMapping("/set-password")
    public String setPassword(@Validated(Default.class) @ModelAttribute("user") User user, BindingResult bindingResult,
                              @ModelAttribute("passwordModel") PasswordModel passwordModel) {
        if (bindingResult.hasErrors()) {
            return "set-password";
        }
        if (user.getPassword().equals(passwordModel.getConfirmPassword())) {
            userService.resetPassword(user.getId(), passwordModel.getConfirmPassword());
        }
        return "reset-password-success";
    }

    @GetMapping("/about-company")
    public String aboutCompanyAction () {
        return "about-us";
    }

    @GetMapping("/designers")
    public String designersPageAction () {
        return "designersListHomeView";
    }

    @GetMapping("our-offer")
    public String companyOfferAction () {
        return "offer";
    }

    @GetMapping("realisations")
    public String realisationsListAction () {
        return "realisations";
    }
}
