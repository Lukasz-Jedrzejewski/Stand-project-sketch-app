package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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

    @GetMapping("/add-logo")
    public String addLogoGetAction() {
        return "/admin/logoForm";
    }

    @PostMapping("/add-logo")
    public String addLogoPostAction(@RequestParam MultipartFile file) throws IOException {
        companyInfoService.addLogo(file);
        return "redirect:/admin/about-company";
    }

    @GetMapping("/edit-company-info/{id}")
    public String editCompanyInfoGetAction(Model model, @PathVariable long id) {
        model.addAttribute("companyInfo", companyInfoService.getOne(id));
        return "/admin/companyInfoForm";
    }

    @PostMapping("/edit-company-info")
    public String editCompanyInfoPostAction(@ModelAttribute CompanyInfo companyInfo){
        companyInfoService.edit(companyInfo);
        return "redirect:/admin/about-company";
    }


}
