package com.legion.standprojectapp.converter;

import com.legion.standprojectapp.entity.FloorBoard;
import com.legion.standprojectapp.repository.FloorBoarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class TypeOfFloorBoardConverter implements Converter<String, FloorBoard> {
    @Autowired
    private FloorBoarRepository floorBoarRepository;

    @Override
    public FloorBoard convert(String id) {
        return floorBoarRepository.getOne(Long.parseLong(id));
    }
}
