package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.service.UserServiceImpl;
import com.legion.standprojectapp.validation.groups.UserEditValidationGroup;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/register")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Validated(Default.class) @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userServiceImpl.save(user);
        return "redirect:/user/about";

    }

    @GetMapping("/about")
    public String about(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        if (!userServiceImpl.checkRole(user.getId()))
            return "panel";
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
}
