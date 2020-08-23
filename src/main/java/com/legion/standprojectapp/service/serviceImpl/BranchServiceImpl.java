package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.service.BranchService;
import com.legion.standprojectapp.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch getOne(long id) {
        return branchRepository.getOne(id);
    }

    @Override
    public void save(Branch branch){
        boolean existBranch = existsByName(branch.getName());
        if (!existBranch) {
            this.branchRepository.save(branch);
        }
    }

    @Override
    public void delete(long id) {
        this.branchRepository.delete(getOne(id));
    }

    @Override
    public boolean existsByName(String name) {
        return branchRepository.existsBranchByName(name);
    }
}
