package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomePageNavigationController {

    private final CompanyInfoServiceImpl companyInfoService;

    public HomePageNavigationController(CompanyInfoServiceImpl companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    @GetMapping("/about-company")
    public String aboutCompanyAction () {
        return "about-us";
    }

    @GetMapping("/designers")
    public String designersPageAction () {
        return "designersListHomeView";
    }

    @GetMapping("our-offer")
    public String companyOfferAction () {
        return "offer";
    }

    @GetMapping("realisations")
    public String realisationsListAction () {
        return "realisations";
    }

    @ModelAttribute("companyInfo")
    public List<CompanyInfo> loadCompanyInfo() {
        return companyInfoService.findAll();
    }
}
