package com.legion.standprojectapp.converter;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.TypeOfBuilding;
import com.legion.standprojectapp.repository.BranchRepository;
import com.legion.standprojectapp.repository.TypeOfBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class TypeOfBranchConverter implements Converter<String, Branch> {
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch convert(String id) {
        return branchRepository.getOne(Long.parseLong(id));
    }
}
