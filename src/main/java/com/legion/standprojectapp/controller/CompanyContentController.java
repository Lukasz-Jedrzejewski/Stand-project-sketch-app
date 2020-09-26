package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CompanyContentController {

    private final CompanyInfoServiceImpl companyInfoService;

    public CompanyContentController(CompanyInfoServiceImpl companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    @GetMapping("/about-company")
    public String menageAboutCompanyGetAction(Model model) {
        model.addAttribute("companyInfo", companyInfoService.findAll());
        return "menageCompanyInfo";
    }

    @GetMapping("/edit-company-info/{id}")
    public String editCompanyIfoGetAction(Model model, @PathVariable long id) {
        model.addAttribute("companyInfo", companyInfoService.getOne(id));
        return "companyInfoForm";
    }




}
