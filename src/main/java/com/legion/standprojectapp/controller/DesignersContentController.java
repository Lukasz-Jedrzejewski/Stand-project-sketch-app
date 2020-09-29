package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.Photography;
import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.PhotographyServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DesignersContentController {

    private final DesignerServiceImpl designerService;
    private final PhotographyServiceImpl photographyService;

    public DesignersContentController(DesignerServiceImpl designerService, PhotographyServiceImpl photographyService) {
        this.designerService = designerService;
        this.photographyService = photographyService;
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

    @GetMapping("/add-designer-photo/{id}")
    public String addDesignerPhotoGetAction(Model model, @PathVariable long id, HttpSession session) {
        model.addAttribute("files", new Photography());
        session.setAttribute("des", designerService.getOne(id));
        return "designerPhotoForm";
    }

    @PostMapping("/add-designer-photo")
    public String addDesignerPhotoPostAction(@ModelAttribute ("photography") MultipartFile[] files,
                                         HttpSession session) {
        Designer designer1 = (Designer) session.getAttribute("des");
        for (MultipartFile file1 : files) {
            photographyService.save(file1, designer1);
        }
        return "redirect:/admin/designers";
    }

    @GetMapping("/edit-designer-info/{id}")
    public String editDesignerInfoGetAction(Model model, @PathVariable long id) {
        model.addAttribute("designer", designerService.getOne(id));
        return "designerForm";
    }

    @PostMapping("/edit-designer-info")
    public String editDesignerInfoPostAction(@ModelAttribute Designer designer) {
        designerService.edit(designer);
        return "redirect:/admin/designers";
    }
}
