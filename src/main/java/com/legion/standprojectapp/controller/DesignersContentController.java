package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DesignersContentController {

    private final DesignerServiceImpl designerService;

    public DesignersContentController(DesignerServiceImpl designerService) {
        this.designerService = designerService;
    }

    @GetMapping("/designers")
    public String getDesignersListAction(Model model) {
        model.addAttribute("designers", designerService.findAll());
        return "designersListForAdmin";
    }
}
