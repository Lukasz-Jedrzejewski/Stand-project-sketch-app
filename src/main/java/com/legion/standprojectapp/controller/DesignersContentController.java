package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.Photography;
import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
import com.legion.standprojectapp.service.serviceImpl.PhotographyServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class DesignersContentController {

    @Value("${images.path}")
    private String path;

    private final DesignerServiceImpl designerService;
    private final PhotographyServiceImpl photographyService;

    public DesignersContentController(DesignerServiceImpl designerService, PhotographyServiceImpl photographyService) {
        this.designerService = designerService;
        this.photographyService = photographyService;
    }

    @GetMapping("/designers")
    public String getDesignersListAction(Model model) {
        model.addAttribute("designers", photographyService.findAll());
        return "/admin/designersListForAdmin";
    }

    @GetMapping("/designer-details/{id}")
    public String getDesignerDetailsAction(Model model, @PathVariable long id) {
        model.addAttribute("designer", designerService.getOne(id));
        model.addAttribute("photography", photographyService.getByDesignerId(id));
        return "/admin/designerDetailsForAdmin";
    }

    @GetMapping("/designer-info")
    public String addDesignerInfoGetAction(Model model){
        model.addAttribute("designer", new Designer());
        return "/admin/designerForm";
    }

    @GetMapping("/designer-info/{id}")
    public String editDesignerInfoGetAction(Model model, @PathVariable long id) {
        model.addAttribute("designer", designerService.getOne(id));
        return "/admin/designerForm";
    }

    @PostMapping("/designer-info")
    public String saveDesignerInfoPostAction(@ModelAttribute Designer designer) {
        designerService.save(designer);
        return "redirect:/admin/designers";
    }

    @GetMapping("/delete-designer/{id}")
    public String deleteDesignerAction(@PathVariable Long id) {
        designerService.deleteDesigner(id);
        return "redirect:/admin/designers";
    }

    /*
    Photography operations
     */

    @GetMapping("/add-designer-photo/{id}")
    public String addDesignerPhotoGetAction(Model model, @PathVariable long id, HttpSession session) {
        model.addAttribute("files", new Photography());
        session.setAttribute("des", designerService.getOne(id));
        return "/admin/designerPhotoForm";
    }

    @PostMapping("/add-designer-photo")
    public String addDesignerPhotoPostAction(@ModelAttribute ("photography") MultipartFile[] files,
                                             HttpSession session) throws IOException {
        Designer designer1 = (Designer) session.getAttribute("des");
        for (MultipartFile file1 : files) {
            boolean existingPhoto = photographyService.existsByDesignerId(designer1.getId());
            if (!existingPhoto) {
                photographyService.save(file1, path + file1.getOriginalFilename(), designer1);
            }
        }
        return "redirect:/admin/designers";
    }

    @GetMapping("/delete-designer-photo/{id}")
    public String deleteDesignerPhotoAction(@PathVariable long id, String filename) throws IOException {
        String fileName = photographyService.getByDesignerId(id).getFileName();
        photographyService.delete(id, path+fileName);
        return "redirect:/admin/designers";
    }
}
