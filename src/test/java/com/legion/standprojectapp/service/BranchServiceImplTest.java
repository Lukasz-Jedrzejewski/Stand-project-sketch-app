package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.repository.BranchRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
class BranchServiceImplTest {

    @InjectMocks
    BranchServiceImpl branchService;

    @Mock
    BranchRepository branchRepository;

    private static final long ID = 1;

    @Before
    public void setUp(){
        this.branchService = new BranchServiceImpl(branchRepository);

    }
    
    @Test
    void findAll() {
        List<Branch> branches = branchService.findAll();
        assertNotNull(branches);
    }

    @Test
    void getOne() {
        branchService.getOne(ID);
        verify(branchRepository).getOne(ID);
    }

    @Test
    void save() {
        Branch branch = mock(Branch.class);
        branchService.save(branch);
        verify(branchRepository).save(branch);

    }

    @Test
    void delete() {
        branchService.delete(ID);
        verify(branchRepository).delete(branchService.getOne(ID));

    }
}