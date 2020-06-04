package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.fixture.InitData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private InitData initData;

    public HomeController(InitData initData) {
        this.initData = initData;
    }

    @GetMapping("/home")
    public String home() {
        initData.initRoles();
        return "home";
    }
}
