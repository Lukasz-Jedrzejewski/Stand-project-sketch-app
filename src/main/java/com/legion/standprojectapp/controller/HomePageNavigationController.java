package com.legion.standprojectapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageNavigationController {

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
}
