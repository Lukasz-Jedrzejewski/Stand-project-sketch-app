package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.repository.CurrentEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentEventService {
    private CurrentEventRepository currentEventRepository;

    public CurrentEventService(CurrentEventRepository currentEventRepository) {
        this.currentEventRepository = currentEventRepository;
    }

    public void save(CurrentEvent currentEvent){
        this.currentEventRepository.save(currentEvent);
    }

    public List<CurrentEvent> findAll() {
        return currentEventRepository.findAll();
    }
}
