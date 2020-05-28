package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "login";
        }

        userServiceImpl.save(user);
        return "redirect:/user/about";

    }

    @GetMapping("/about")
    public String about() {
        return "panel";
    }

}
