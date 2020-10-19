package com.legion.standprojectapp.controller;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.JOpenCageServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.PhotographyServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;

@Controller
public class HomePageNavigationController {

    private final CompanyInfoServiceImpl companyInfoService;
    private final PhotographyServiceImpl photographyService;
    private final JOpenCageServiceImpl jOpenCageService;

    public HomePageNavigationController(CompanyInfoServiceImpl companyInfoService, PhotographyServiceImpl photographyService, JOpenCageServiceImpl jOpenCageService) {
        this.companyInfoService = companyInfoService;
        this.photographyService = photographyService;
        this.jOpenCageService = jOpenCageService;
    }

    @GetMapping("/about-company")
    public String aboutCompanyAction (Model model) {
        List<CompanyInfo> info = companyInfoService.findAll();
        String city = "";
        String street = "";
        for (CompanyInfo companyInfo : info) {
            city = companyInfo.getCity();
            street = companyInfo.getStreet() + " " + companyInfo.getBuildingNumber();
        }
        JOpenCageLatLng coordinates = jOpenCageService.getCoordinates(street, city);
        model.addAttribute("companyInfo", companyInfoService.findAll());
        model.addAttribute("coordinates", coordinates);
        return "about-us";
    }

    @GetMapping("/designers")
    public String designersPageAction (Model model) {
        model.addAttribute("photos", photographyService.findAll());
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

//    @ModelAttribute("companyInfo")
//    public List<CompanyInfo> loadCompanyInfo() {
//        return companyInfoService.findAll();
//    }
}
