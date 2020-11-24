package com.legion.standprojectapp.controller;

import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.service.serviceImpl.CompanyInfoServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.JOpenCageServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.RealizationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomePageNavigationController {

    private final CompanyInfoServiceImpl companyInfoService;
    private final JOpenCageServiceImpl jOpenCageService;
    private final DesignerServiceImpl designerService;
    private final RealizationServiceImpl realizationService;

    public HomePageNavigationController(CompanyInfoServiceImpl companyInfoService, JOpenCageServiceImpl jOpenCageService, DesignerServiceImpl designerService, RealizationServiceImpl realizationService) {
        this.companyInfoService = companyInfoService;
        this.jOpenCageService = jOpenCageService;
        this.designerService = designerService;
        this.realizationService = realizationService;
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
        model.addAttribute("logo", companyInfoService.getOne(1));
        return "/home/about-us";
    }

    @GetMapping("/designers")
    public String designersPageAction (Model model) {
        model.addAttribute("designer", designerService.findAll());
        model.addAttribute("logo", companyInfoService.getOne(1));
        return "/home/designersListHomeView";
    }

    @GetMapping("our-offer")
    public String companyOfferAction (Model model) {
        model.addAttribute("logo", companyInfoService.getOne(1));
        return "/home/offer";
    }

    @GetMapping("realisations")
    public String realisationsListAction (Model model) {
        model.addAttribute("realizations", realizationService.findAll());
        model.addAttribute("logo", companyInfoService.getOne(1));
        return "/home/realisations";
    }
}
