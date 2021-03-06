package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.service.serviceImpl.CurrentEventServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller

@RequestMapping("/event")
public class CurrentEventController {

    private CurrentEventServiceImpl currentEventServiceImpl;

    public CurrentEventController(CurrentEventServiceImpl currentEventServiceImpl) {
        this.currentEventServiceImpl = currentEventServiceImpl;
    }

    @ModelAttribute("events")
    public List<CurrentEvent> events () {
        return currentEventServiceImpl.findAll();
    }

    @GetMapping("/get")
    public String getEvent(Model model) {
        model.addAttribute("currentEvent", new CurrentEvent());
        return "/user/event";
    }

    @GetMapping("/project/prepare/{id}")
    public String readCurrentEvent(@PathVariable long id, HttpSession session) {
        session.setAttribute("currentEvent", currentEventServiceImpl.getOne(id));
        return "redirect:/branch/get";
    }

    @PostMapping("/get")
    public String saveEvent (@Valid @ModelAttribute CurrentEvent currentEvent, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "/user/event";
        }

        session.setAttribute("currentEvent", currentEvent);

        currentEventServiceImpl.save(currentEvent);
        return "redirect:/branch/get";
    }
}
