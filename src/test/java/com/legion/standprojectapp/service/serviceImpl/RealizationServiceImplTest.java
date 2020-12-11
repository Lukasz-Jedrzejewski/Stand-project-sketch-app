package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Realization;
import com.legion.standprojectapp.repository.RealizationRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandProjectAppApplication.class)
class RealizationServiceImplTest {

    @Autowired
    private RealizationServiceImpl realizationService;

    @Autowired
    private RealizationRepository realizationRepository;

    Realization realization = new Realization();
    MockMultipartFile jpgFile =
            new MockMultipartFile("image", "1", "image/jpg", "someValue".getBytes());

    @BeforeEach
    public void init() throws IOException {
        realizationService.savePic(realization, jpgFile, jpgFile.getOriginalFilename());
    }

    @AfterEach
    public void clear() throws IOException {
        realizationService.deletePic(realization.getId(), realization.getFileName());
    }

    @Test
    @DisplayName("test savePic")
    @Transactional
    void savePic() throws IOException {
        assertAll(
                () -> {
                    assertNotNull(realizationService.findOne(realization.getId()),
                            "realization should be saved");
                },
                () -> {
                    assertEquals(1, realizationService.findAll().size(),
                            "realization should be saved");
                },
                () -> {
                    assertFalse(realizationRepository.getOne(realization.getId()).isImportant(),
                            "the default value should be false");
                },
                () -> {
                    Path path = Paths.get("src/main/webapp/resources/images/realizations/"+jpgFile.getOriginalFilename());
                    assertTrue(Files.exists(path), "image should be saved in path");
                }
        );
    }

    @Test
    @DisplayName("test deletePic")
    void deletePic() throws IOException {
        assertFalse(realizationService.findAll().isEmpty());
        realizationService.deletePic(realization.getId(), realization.getFileName());
        assertTrue(realizationService.findAll().isEmpty());
    }

    @Test
    @DisplayName("test findAll")
    void findAll() throws IOException {
        assertEquals(1, realizationService.findAll().size(),
                "One element should be saved");
        Realization nextOne = new Realization();
        MockMultipartFile anotherJpgFile =
                new MockMultipartFile("image", "2", "image/jpg", "someValue".getBytes());
        realizationService.savePic(nextOne, anotherJpgFile, anotherJpgFile.getOriginalFilename());
        assertEquals(2, realizationService.findAll().size(),
                "Two elements should be saved");
        realizationService.deletePic(nextOne.getId(), nextOne.getFileName());
    }

    @Test
    @DisplayName("test findOne")
    @Transactional
    void findOne() {
        assertNotNull(realizationService.findOne(realization.getId()), "One element should be saved");
        assertEquals(realizationService.findOne(realization.getId()).getFileName(), realization.getFileName(),
                "Names of file should be the same");
    }

    @Test
    @DisplayName("test setImportant")
    @Transactional
    void setImportant() {
        assertFalse(realizationService.findOne(realization.getId()).isImportant(), "Important property should be false");
        realizationService.setImportant(realization.getId());
        assertTrue(realizationService.findOne(realization.getId()).isImportant(), "Important property should be true");
        realizationService.setImportant(realization.getId());
        assertFalse(realizationService.findOne(realization.getId()).isImportant(), "Important property should be false");
    }

    @Test
    @DisplayName("test findAllImportant")
    @Transactional
    void findAllImportant() {
        assertTrue(realizationService.findAllImportant().isEmpty(), "List should be empty. \n" +
                "The default value of the important property is false");
        realizationService.setImportant(realization.getId());
        assertEquals(1, realizationService.findAllImportant().size(),
                "It should find one element with the important property set to true");
    }
}