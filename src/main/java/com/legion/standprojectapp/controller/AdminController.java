package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.service.BranchServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private BranchServiceImpl branchService;

    public AdminController(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/adminPanel")
    public String getAdminPanel(){
        return "adminPanel";
    }

    @GetMapping("/branches")
    public String branchList(Model model){
        model.addAttribute("branchList", branchService.findAll());
        return "branchList";
    }
}
