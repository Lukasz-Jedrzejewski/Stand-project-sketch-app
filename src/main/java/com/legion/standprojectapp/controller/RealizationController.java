package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Realization;
import com.legion.standprojectapp.service.serviceImpl.RealizationServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class RealizationController {

    @Value("${realizations.path}")
    private String path;

    private final RealizationServiceImpl realizationService;

    public RealizationController(RealizationServiceImpl realizationService) {
        this.realizationService = realizationService;
    }

    @GetMapping("/realizations")
    public String getAllRealisations(Model model) {
        model.addAttribute("realizations", realizationService.findAll());
        return "/admin/realizations-admin-panel";
    }

    @GetMapping("/add-realizations")
    public String addRealisationsGetAction (Model model) {
        model.addAttribute("realization", new Realization());
        return "/admin/realizations-form";
    }

    @PostMapping("/add-realizations")
    public String addRealisationsPostAction(@RequestParam MultipartFile file,
                                            @ModelAttribute Realization realization) throws IOException {
        realizationService.savePic(realization, file, path+file.getOriginalFilename());
        return "redirect:/admin/realizations";
    }

    @GetMapping("/set-important/{id}")
    public String setImportantValueAction (@PathVariable long id) {
        System.out.println(realizationService.findOne(id).getId());
        System.out.println(realizationService.findOne(id).getFileName());
        System.out.println(realizationService.findOne(id).isImportant());
        realizationService.setImportant(id);
        return "redirect:/admin/realizations";
    }
}
