package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class CompanyContentController {

    @Value("${images.path}")
    private String path;

    private final CompanyInfoServiceImpl companyInfoService;

    public CompanyContentController(CompanyInfoServiceImpl companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    @GetMapping("/about-company")
    public String menageAboutCompanyGetAction(Model model) {
        model.addAttribute("companyInfo", companyInfoService.findAll());
        return "/admin/menageCompanyInfo";
    }

    @GetMapping("/add-logo/{id}")
    public String addLogoGetAction(Model model, @PathVariable long id, HttpSession session) {
        model.addAttribute("companyInfo", companyInfoService.getOne(id));
        session.setAttribute("info", companyInfoService.getOne(id));
        return "/admin/logoForm";
    }

    @PostMapping("/add-logo")
    public String addLogoPostAction(@RequestParam MultipartFile file, @ModelAttribute CompanyInfo companyInfo,
                                    HttpSession session) throws IOException {
        CompanyInfo info = (CompanyInfo) session.getAttribute("info");
        String fileName = file.getOriginalFilename();
        String logoNameByCompanyInfoId = companyInfoService.getLogoNameByCompanyInfoId(info.getId());
        companyInfoService.deleteLogo(path+logoNameByCompanyInfoId);
        companyInfoService.addLogo(info, path+fileName, file);
        return "redirect:/admin/about-company";
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
