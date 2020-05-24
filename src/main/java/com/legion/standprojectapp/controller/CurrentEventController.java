package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.repository.CurrentEventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/event")
public class CurrentEventController {

    private CurrentEventRepository currentEventRepository;

    public CurrentEventController(CurrentEventRepository currentEventRepository) {
        this.currentEventRepository = currentEventRepository;
    }

    @ModelAttribute("events")
    public List<CurrentEvent> events () {
        return currentEventRepository.findAll();
    }

    @GetMapping("/get")
    public String getEvent(Model model) {
        model.addAttribute("currrentEvent", new CurrentEvent());
        return "event";
    }

    @PostMapping("/get")
    public String saveEvent (@Valid @ModelAttribute CurrentEvent currentEvent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "event";
        }
        currentEventRepository.save(currentEvent);
        return "redirect:/project/add";
    }
}
