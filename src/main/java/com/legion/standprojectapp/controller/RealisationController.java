package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.service.serviceImpl.RealisationServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RealisationController {

    @Value("${realisations.path}")
    private String path;

    private final RealisationServiceImpl realisationService;

    public RealisationController(RealisationServiceImpl realisationService) {
        this.realisationService = realisationService;
    }

    @GetMapping("/realisations")
    public String getAllRealisations(Model model) {
        model.addAttribute("realisations", realisationService.findAll());
        return "/admin/realisations";
    }
}
