package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "/admin/menageCompanyInfo";
    }

    @GetMapping("/edit-company-info/{id}")
    public String editCompanyIfoGetAction(Model model, @PathVariable long id) {
        model.addAttribute("companyInfo", companyInfoService.getOne(id));
        return "/admin/companyInfoForm";
    }

    @PostMapping("/edit-company-info")
    public String editCompanyInfoPostAction(@ModelAttribute CompanyInfo companyInfo){
        companyInfoService.edit(companyInfo);
        return "redirect:/admin/about-company";
    }


}
