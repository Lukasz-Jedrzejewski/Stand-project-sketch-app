package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.File;
import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.FileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class DesignersContentController {

    private final DesignerServiceImpl designerService;
    private final FileServiceImpl fileService;

    public DesignersContentController(DesignerServiceImpl designerService, FileServiceImpl fileService) {
        this.designerService = designerService;
        this.fileService = fileService;
    }

    @GetMapping("/designers")
    public String getDesignersListAction(Model model) {
        model.addAttribute("designers", designerService.findAll());
        return "designersListForAdmin";
    }

    @GetMapping("/designer-details/{id}")
    public String getDesignerDetailsAction(Model model, @PathVariable long id) {
        model.addAttribute("designer", designerService.getOne(id));
        return "designerDetailsForAdmin";
    }

    @GetMapping("/edit-designer/{id}")
    public String editDesignerGetAction(Model model, @PathVariable long id) {
        model.addAttribute("designer", designerService.getOne(id));
        return "designerForm";
    }

    @PostMapping("/edit-designer")
    public String editDesignerPostAction(@ModelAttribute ("designer")Designer designer) {
        designerService.save(designer);
        return "redirect:/admin/designers";
    }
}
