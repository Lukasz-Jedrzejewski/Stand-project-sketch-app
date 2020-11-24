package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.PasswordResetToken;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.entity.VerificationToken;
import com.legion.standprojectapp.model.ContactMessage;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.model.EmailModel;
import com.legion.standprojectapp.model.PasswordModel;
import com.legion.standprojectapp.service.serviceImpl.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final CompanyInfoServiceImpl companyInfoService;

    public HomeController(UserServiceImpl userService, VerificationTokenServiceImpl verificationTokenService, MailServiceImpl mailService, PasswordResetTokenServiceImpl passwordResetTokenService, CompanyInfoServiceImpl companyInfoService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.mailService = mailService;
        this.passwordResetTokenService = passwordResetTokenService;
        this.companyInfoService = companyInfoService;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        try {
            User user = currentUser.getUser();
            if(user != null) {
                model.addAttribute("user", user);
            }
        } catch (NullPointerException e) {
            System.out.println("Logged user = " + e.getMessage());
        }
        return "/home/home";
    }

    @GetMapping("/register")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("passwordModel", new PasswordModel());
        return "/home/register";
    }

    @PostMapping("/register")
    public String addUser(@Validated(Default.class) @ModelAttribute("user") User user,
                          BindingResult bindingResult,
                          @ModelAttribute("passwordModel") PasswordModel passwordModel) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "/home/register";
        }
        if (user.getPassword().equals(passwordModel.getConfirmPassword())) {
            user.setEnabled(false);
            userService.save(user);
            VerificationToken verificationToken = new VerificationToken(user);
            verificationTokenService.save(verificationToken);
            mailService.sendVerificationToken(user.getCompanyMail(), verificationToken.getToken());
        } else {
            return "/home/register";
        }
        return "/home/register-verify";
    }

    @RequestMapping(value = "/confirm-register", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmRegister(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findToken(verificationToken);
        if (token != null) {
            User user = userService.findByCompanyMail(token.getUser().getCompanyMail());
            user.setEnabled(true);
            userService.editUser(user);
            return "/home/register-successfully";
        } else {
            return "/home/register-failed";
        }
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model){
        model.addAttribute("emailModel", new EmailModel());
        return "/home/reset-password";
    }

    @PostMapping("/reset-password")
    public String postResetPassword(@ModelAttribute EmailModel emailModel) throws MessagingException {
        String email = emailModel.getEmail();
        PasswordResetToken passwordResetToken = new PasswordResetToken(userService.findByCompanyMail(email));
        passwordResetTokenService.save(passwordResetToken);
        mailService.sendPasswordResetToken(email, passwordResetToken.getToken());
        return "/home/reset-password-info";
    }

    @RequestMapping(value = "/reset-confirmation", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmPasswordReset(@RequestParam("token") String passwordResetToken, Model model) {
        PasswordResetToken token = passwordResetTokenService.findToken(passwordResetToken);
        if (token != null) {
            User user = userService.findByCompanyMail(token.getUser().getCompanyMail());
            model.addAttribute("user", user);
            model.addAttribute("passwordModel", new PasswordModel());
            return "/home/set-password";
        } else {
            return "/home/set-password-failed";
        }
    }

    @PostMapping("/set-password")
    public String setPassword(@Validated(Default.class) @ModelAttribute("user") User user, BindingResult bindingResult,
                              @ModelAttribute("passwordModel") PasswordModel passwordModel) {
        if (bindingResult.hasErrors()) {
            return "/home/set-password";
        }
        if (user.getPassword().equals(passwordModel.getConfirmPassword())) {
            userService.resetPassword(user.getId(), passwordModel.getConfirmPassword());
        }
        return "/home/reset-password-success";
    }

    @PostMapping("/send-contact-message")
    public String sendContactMessageAction (@ModelAttribute ContactMessage contactMessage) throws MessagingException {
        String adminEmail = userService.findAdmin().getCompanyMail();
        mailService.sendContactMessage(adminEmail, "Proszę o kontakt", contactMessage.toHtml());
        return "redirect:/";
    }
}
