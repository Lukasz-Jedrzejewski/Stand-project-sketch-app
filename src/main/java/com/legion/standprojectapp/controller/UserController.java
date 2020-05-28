package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.model.CurrentUser;
import com.legion.standprojectapp.service.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/about")
    @ResponseBody
    public String about(
            @AuthenticationPrincipal CurrentUser currentUser
            ) {
        return currentUser.getUser().getCompanyMail();

//        return "panel";
    }

    @GetMapping("/403")
    public String page403(){
        return "403";
    }

}
