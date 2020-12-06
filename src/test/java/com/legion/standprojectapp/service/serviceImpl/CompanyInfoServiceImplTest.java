package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.repository.CompanyInfoRepository;
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

    @Test
    @DisplayName("findAll CompanyInfo method testing")
    void findAll() {
        assertEquals(1, companyInfoService.findAll().size(),
                "Should be 1, " +
                        "because the basic information about the company is saved at the start of the application");
    }

    @Test
    @DisplayName("save CompanyInfo method testing")
    @Transactional
    void save() {
        CompanyInfo newInfo = new CompanyInfo();
        newInfo.setDescription("test save method");
        assertAll(
                () -> {
                    assertEquals(1, companyInfoService.findAll().size(),
                            "In database should be only one element");
                    companyInfoService.save(newInfo);
                    boolean newElement = companyInfoService.findAll().size() == 1;
                    assertTrue(newElement, "Impossible to save another CompanyInfo if One exist");
                },
                () -> {
                    companyInfoRepository.deleteAll();
                    assertEquals(0, companyInfoService.findAll().size(),
                            "List should be empty");
                    companyInfoService.save(newInfo);
                    assertEquals(newInfo.getDescription(), companyInfoRepository.getOne(newInfo.getId()).getDescription(),
                            "Element should be saved, because size of the list is less than 1");
                }
        );

    }

    @Test
    void addLogo() {
    }

    @Test
    @DisplayName("test getOne method")
    void getOne() {
        assertNotNull(companyInfoService.getOne(1), "Should be basic CompanyInfo saved id DB");
    }

    @Test
    @DisplayName("test edit method")
    @Transactional
    void edit() {
        CompanyInfo basic = companyInfoService.getOne(1);
        assertAll(
                () -> {
                    basic.setDescription("new description");
                    companyInfoService.edit(basic);
                    assertEquals("new description", companyInfoService.getOne(1).getDescription(),
                            "Description should be changed");
                },
                () -> {
                    basic.setId((long) 3);
                    basic.setDescription("Another description");
                    basic.setApartmentNumber("12");
                    basic.setBuildingNumber("5");
                    basic.setCity("Warsaw");
                    basic.setCountry("Poland");
                    basic.setStreet("Kacza");
                    basic.setZipCode("11-111");
                    companyInfoService.edit(basic);
                    assertAll(
                            () -> {
                                assertEquals("Another description", companyInfoService.getOne(1).getDescription(),
                                        "Description should be changed");
                            },
                            () -> {
                                CompanyInfo thisInfo = companyInfoService.getOne(basic.getId());
                                boolean newId = thisInfo.getId() == 3;
                                assertTrue(newId, "Id should be changed");
                            },
                            () -> {
                                assertEquals("12", companyInfoService.getOne(1).getApartmentNumber(),
                                        "Apartment number should be changed");
                            },
                            () -> {
                                assertEquals("5", companyInfoService.getOne(1).getBuildingNumber(),
                                        "Building number should be changed");
                            },
                            () -> {
                                assertEquals("Warsaw", companyInfoService.getOne(1).getCity(),
                                        "City should be changed");
                            },
                            () -> {
                                assertEquals("Poland", companyInfoService.getOne(1).getCountry(),
                                        "Country should be changed");
                            },
                            () -> {
                                assertEquals("Kacza", companyInfoService.getOne(1).getStreet(),
                                        "Street should be changed");
                            },
                            () -> {
                                assertEquals("11-111", companyInfoService.getOne(1).getZipCode(),
                                        "Zip code should be changed");
                            }
                    );
                }
        );
    }
}