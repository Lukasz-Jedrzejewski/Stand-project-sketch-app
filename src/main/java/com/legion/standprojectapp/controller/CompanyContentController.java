package com.legion.standprojectapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CompanyContentController {

    @GetMapping("/about-company")
    public String menageAboutCompanyGetAction(Model model) {

        return "menageCompanyInfo";
    }
}
