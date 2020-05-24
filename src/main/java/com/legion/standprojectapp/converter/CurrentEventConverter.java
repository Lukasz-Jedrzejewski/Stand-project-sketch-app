package com.legion.standprojectapp.converter;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.repository.CurrentEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class CurrentEventConverter implements Converter<String, CurrentEvent> {
    @Autowired
    private CurrentEventRepository currentEventRepository;

    @Override
    public CurrentEvent convert(String id) {
        return currentEventRepository.getOne(Long.parseLong(id));
    }
}
