package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.service.CurrentEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

@RequestMapping("/event")
public class CurrentEventController {

    private CurrentEventService currentEventService;

    public CurrentEventController(CurrentEventService currentEventService) {
        this.currentEventService = currentEventService;
    }

    @ModelAttribute("events")
    public List<CurrentEvent> events () {
        return currentEventService.findAll();
    }

    @GetMapping("/get")
    public String getEvent(Model model) {
        model.addAttribute("currrentEvent", new CurrentEvent());
        return "event";
    }

    @PostMapping("/get")
    public String saveEvent (@Valid @ModelAttribute CurrentEvent currentEvent, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "event";
        }

        session.setAttribute("event", currentEvent);

        currentEventService.save(currentEvent);
        return "redirect:/project/add";
    }
}
