package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.service.CurrentEventService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("currentEvent", new CurrentEvent());
        return "event";
    }

    @GetMapping("/project/prepare/{id}")

    public String readCurrentEvent(@PathVariable long id, HttpSession session) {
        session.setAttribute("currentEvent", currentEventService.getOne(id));
        return "redirect:/project/add";
    }

    @PostMapping("/get")
    public String saveEvent (@Valid @ModelAttribute CurrentEvent currentEvent, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "event";
        }

        session.setAttribute("currentEvent", currentEvent);

        currentEventService.save(currentEvent);
        return "redirect:/project/add";
    }
}
