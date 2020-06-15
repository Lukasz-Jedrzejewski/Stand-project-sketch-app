package com.legion.standprojectapp.interfaces;

import com.legion.standprojectapp.entity.TypeOfBuilding;

import java.util.List;

public interface TypeOfBuildingService {
    List<TypeOfBuilding> findAll();
    TypeOfBuilding getOne(long id);
    void save(TypeOfBuilding typeOfBuilding);
    void delete(long id);
    boolean existsByName(String name);
}
