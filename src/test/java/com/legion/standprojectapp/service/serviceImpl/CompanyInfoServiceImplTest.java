package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.repository.CompanyInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandProjectAppApplication.class)
class CompanyInfoServiceImplTest {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private CompanyInfoServiceImpl companyInfoService;

    @BeforeEach
    public void init() {
        CompanyInfo created = new CompanyInfo();
        companyInfoRepository.save(created);
    }

    @Test
    @DisplayName("findAll CompanyInfo method testing")
    void findAll() {
        assertAll(
                () -> {
                    assertEquals(2, companyInfoService.findAll().size(),
                            "Should be 2, " +
                                    "because the basic information about the company is saved at the start of the application");
                },
                () -> {
                    companyInfoRepository.deleteAll();
                    assertEquals(0, companyInfoService.findAll().size(),
                            "list should be empty");
                }
        );
    }

    @Test
    @DisplayName("save CompanyInfo method testing")
    @Transactional
    void save() {
        CompanyInfo newInfo = new CompanyInfo();
        newInfo.setId((long) 3);
        newInfo.setDescription("test save method");
        assertAll(
                () -> {
                    assertEquals(2, companyInfoService.findAll().size());
                    companyInfoService.save(newInfo);
                    assertEquals(2, companyInfoService.findAll().size(), "Impossible to save another CompanyInfo if One exist");
                },
                () -> {
                    companyInfoRepository.deleteAll();
                    companyInfoService.save(newInfo);
                    assertEquals(newInfo.getDescription(), companyInfoRepository.getOne(newInfo.getId()).getDescription(),
                            "Descriptions should be the same");
                }
        );

    }

    @Test
    void addLogo() {
    }

    @Test
    void getOne() {
    }

    @Test
    void edit() {
    }
}