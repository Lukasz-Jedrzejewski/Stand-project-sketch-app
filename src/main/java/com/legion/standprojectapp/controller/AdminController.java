package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.service.BranchServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/deleteBranch/{id}")
    public String deleteBranch(@PathVariable long id){
        branchService.delete(id);
        return "redirect:/admin/branches";
    }

    @GetMapping("/addBranch")
    public String addBranch(Model model) {
        model.addAttribute("branch", new Branch());
        return "adminBranchForm";
    }

    @GetMapping("/addBranch/{id}")
    public String editBranch(Model model, @PathVariable long id) {
        model.addAttribute("branch", branchService.getOne(id));
        return "adminBranchForm";
    }

    @PostMapping("/addBranch")
    public String branchPost(@Valid @ModelAttribute Branch branch, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminBranchForm";
        } else {
            branchService.save(branch);
        }
        return "redirect:/admin/branches";
    }
}
