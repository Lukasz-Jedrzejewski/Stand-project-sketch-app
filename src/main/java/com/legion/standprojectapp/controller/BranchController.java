package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.service.serviceImpl.BranchServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/branch")
public class BranchController {
    private BranchServiceImpl branchService;

    public BranchController(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }

    @ModelAttribute("branches")
    public List<Branch> branches(){
        return branchService.findAll();
    }

    @GetMapping("/get")
    public String getBranch(Model model) {
        model.addAttribute("branch", new Branch());
        return "branch";
    }

    @GetMapping("/project/prepareBranch/{id}")
    public String readCurrentBranch(@PathVariable long id, HttpSession session) {
        session.setAttribute("branch", branchService.getOne(id));
        return "redirect:/project/add";
    }

    @PostMapping("/get")
    public String saveBranch (@Valid @ModelAttribute Branch branch, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "branch";
        }

        session.setAttribute("branch", branch);

        branchService.save(branch);
        return "redirect:/project/add";
    }
}
