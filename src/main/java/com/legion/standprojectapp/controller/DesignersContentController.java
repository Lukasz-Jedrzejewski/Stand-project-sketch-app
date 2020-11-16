package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.service.serviceImpl.DesignerServiceImpl;
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

    public DesignersContentController(DesignerServiceImpl designerService) {
        this.designerService = designerService;
    }

    @GetMapping("/designers")
    public String getDesignersListAction(Model model) {
        model.addAttribute("designers", designerService.findAll());
        return "/admin/designersListForAdmin";
    }

    @GetMapping("/designer-details/{id}")
    public String getDesignerDetailsAction(Model model, @PathVariable long id) {
        model.addAttribute("designer", designerService.getOne(id));
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
    public String deleteDesignerAction(@PathVariable Long id) throws IOException {
        designerService.deleteDesigner(id);
        return "redirect:/admin/designers";
    }

    /*
    Photography operations
     */

    @GetMapping("/add-designer-photo/{id}")
    public String addDesignerPhotoGetAction(@PathVariable long id, HttpSession session) {
        session.setAttribute("des", designerService.getOne(id));
        return "/admin/designerPhotoForm";
    }

    @PostMapping("/add-designer-photo")
    public String addDesignerPhotoPostAction(@RequestParam MultipartFile file, HttpSession session) throws IOException {
        Designer current = (Designer) session.getAttribute("des");
        String fileName = file.getOriginalFilename();
        String picName = designerService.gePicNameByDesignerId(current.getId());
        designerService.deletePic(path+picName);
        designerService.addPic(current, path+fileName, file);
        return "redirect:/admin/designers";
    }

    @GetMapping("/delete-designer-photo/{id}")
    public String deleteDesignerPhotoAction(@PathVariable long id) throws IOException {
        String fileName = designerService.gePicNameByDesignerId(id);
        designerService.clearPic(id, path+fileName);
        return "redirect:/admin/designers";
    }
}
