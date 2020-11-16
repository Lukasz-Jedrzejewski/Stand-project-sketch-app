package com.legion.standprojectapp.controller;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.JOpenCageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomePageNavigationController {

    private final CompanyInfoServiceImpl companyInfoService;
    private final JOpenCageServiceImpl jOpenCageService;
    private final DesignerServiceImpl designerService;

    public HomePageNavigationController(CompanyInfoServiceImpl companyInfoService, JOpenCageServiceImpl jOpenCageService, DesignerServiceImpl designerService) {
        this.companyInfoService = companyInfoService;
        this.jOpenCageService = jOpenCageService;
        this.designerService = designerService;
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
        return "/home/about-us";
    }

    @GetMapping("/designers")
    public String designersPageAction (Model model) {
        model.addAttribute("designer", designerService.findAll());
        return "/home/designersListHomeView";
    }

    @GetMapping("our-offer")
    public String companyOfferAction () {
        return "/home/offer";
    }

    @GetMapping("realisations")
    public String realisationsListAction () {
        return "/home/realisations";
    }
}
