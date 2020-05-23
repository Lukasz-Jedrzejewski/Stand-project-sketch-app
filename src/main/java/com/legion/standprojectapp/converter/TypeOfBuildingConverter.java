package com.legion.standprojectapp.converter;

import com.legion.standprojectapp.entity.TypeOfBuilding;
import com.legion.standprojectapp.repository.TypeOfBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class TypeOfBuildingConverter implements Converter<String, TypeOfBuilding> {
    @Autowired
    private TypeOfBuildingRepository typeOfBuildingRepository;

    @Override
    public TypeOfBuilding convert(String id) {
        return typeOfBuildingRepository.getOne(Long.parseLong(id));
    }
}
