package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.interfaces.CurrentEventService;
import com.legion.standprojectapp.repository.CurrentEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentEventServiceImpl implements CurrentEventService {
    private CurrentEventRepository currentEventRepository;

    public CurrentEventServiceImpl(CurrentEventRepository currentEventRepository) {
        this.currentEventRepository = currentEventRepository;
    }

    @Override
    public void save(CurrentEvent currentEvent){
        boolean existEvent = existsByName(currentEvent.getName());
        if (!existEvent) {
            this.currentEventRepository.save(currentEvent);
        }
    }

    @Override
    public List<CurrentEvent> findAll() {
        return currentEventRepository.findAll();
    }

    @Override
    public CurrentEvent getOne(long id) {
        return currentEventRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        this.currentEventRepository.delete(getOne(id));
    }

    @Override
    public boolean existsByName(String name) {
        return currentEventRepository.existsCurrentEventByName(name);
    }
}
