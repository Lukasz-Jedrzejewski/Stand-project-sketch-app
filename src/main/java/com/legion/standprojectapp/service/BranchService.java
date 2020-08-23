package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Branch;

import java.util.List;

public interface BranchService {
    List<Branch> findAll();
    Branch getOne(long id);
    void save(Branch branch);
    void delete(long id);
    boolean existsByName(String name);
}
