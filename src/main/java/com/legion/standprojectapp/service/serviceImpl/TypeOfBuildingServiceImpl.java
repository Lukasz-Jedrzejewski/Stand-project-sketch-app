package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.TypeOfBuilding;
import com.legion.standprojectapp.service.TypeOfBuildingService;
import com.legion.standprojectapp.repository.TypeOfBuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfBuildingServiceImpl implements TypeOfBuildingService {
    private TypeOfBuildingRepository typeOfBuildingRepository;

    public TypeOfBuildingServiceImpl(TypeOfBuildingRepository typeOfBuildingRepository) {
        this.typeOfBuildingRepository = typeOfBuildingRepository;
    }


    @Override
    public List<TypeOfBuilding> findAll() {
        return typeOfBuildingRepository.findAll();
    }

    @Override
    public TypeOfBuilding getOne(long id) {
        return typeOfBuildingRepository.getOne(id);
    }

    @Override
    public void save(TypeOfBuilding typeOfBuilding){
        boolean existTypeOfBuilding = existsByName(typeOfBuilding.getName());
        if (!existTypeOfBuilding) {
            this.typeOfBuildingRepository.save(typeOfBuilding);
        }
    }

    @Override
    public void delete(long id) {
        this.typeOfBuildingRepository.delete(getOne(id));
    }

    @Override
    public boolean existsByName(String name) {
        return typeOfBuildingRepository.existsTypeOfBuildingByName(name);
    }
}
