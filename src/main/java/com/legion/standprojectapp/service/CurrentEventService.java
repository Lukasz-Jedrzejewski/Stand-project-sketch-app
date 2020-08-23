package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CurrentEvent;

import java.util.List;

public interface CurrentEventService {
    void save(CurrentEvent currentEvent);
    List<CurrentEvent> findAll();
    CurrentEvent getOne(long id);
    void delete(long id);
    boolean existsByName(String name);
}
