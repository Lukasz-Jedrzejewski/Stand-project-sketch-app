package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.repository.DesignerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandProjectAppApplication.class)
class DesignerServiceImplTest {

    @Autowired
    private DesignerServiceImpl designerService;

    @Autowired
    private DesignerRepository designerRepository;

    @AfterEach
    void clear() {
        designerRepository.deleteAll();
    }

    @Test
    @DisplayName("test findAll")
    void findAll() {
        assertAll(
                () -> {
                    assertEquals(0, designerService.findAll().size(), "List should be empty");
                },
                () -> {
                    Designer designer = new Designer();
                    designerRepository.save(designer);
                    assertEquals(1, designerService.findAll().size(), "One element should be saved in database");
                }
        );
    }

    @Test
    @DisplayName("test getOne")
    @Transactional
    void getOne() {
        Designer designer = new Designer();
        designer.setName("Dave");
        designer.setSurname("Jones");
        designer.setDescription("Example description");
        designer.setPhotoName("Example photo name");
        designerRepository.save(designer);
        assertAll(
                () -> {
                    assertNotNull(designerService.getOne(designer.getId()));
                },
                () -> {
                    assertEquals(designer.getName(), designerService.getOne(designer.getId()).getName(),
                            "Should be the same");
                },
                () -> {
                    assertEquals(designer.getSurname(), designerService.getOne(designer.getId()).getSurname(),
                            "Should be the same");
                },
                () -> {
                    assertEquals(designer.getDescription(), designerService.getOne(designer.getId()).getDescription(),
                            "Should be the same");
                },
                () -> {
                    assertEquals(designer.getPhotoName(), designerService.getOne(designer.getId()).getPhotoName(),
                            "Should be the same");
                }
        );
    }

    @Test
    @DisplayName("test save")
    @Transactional
    void save() {
        Designer designer = new Designer();
        System.out.println(designerService.findAll().size());
        designerService.save(designer);
        System.out.println(designerService.findAll().size());
        assertNotNull(designerService.getOne(designer.getId()));
        assertEquals(1, designerService.findAll().size(), "One element should be saved id database");
    }

    @Test
    @DisplayName("test edit")
    @Transactional
    void edit() {
        Designer designer = new Designer();
        designerRepository.save(designer);
        assertAll(
                () -> {
                    designer.setName("Joe");
                    designerService.edit(designer);
                    assertEquals(designer.getName(), designerService.getOne(designer.getId()).getName(),
                            "Names should be the same");
                },
                () -> {
                    designer.setName("Joe");
                    designer.setSurname("Amstrong");
                    designer.setDescription("Musician");
                    designer.setPhotoName("Green day");
                    designerService.edit(designer);
                    assertEquals(designer.getName(), designerService.getOne(designer.getId()).getName(),
                            "Names should be the same");
                    assertEquals(designer.getSurname(), designerService.getOne(designer.getId()).getSurname(),
                            "Surnames should be the same");
                    assertEquals(designer.getDescription(), designerService.getOne(designer.getId()).getDescription(),
                            "Descriptions should be the same");
                    assertEquals(designer.getPhotoName(), designerService.getOne(designer.getId()).getPhotoName(),
                            "Photo names should be the same");
                },
                () -> {
                    designerService.edit(designer);
                    assertEquals(designer.getName(), designerService.getOne(designer.getId()).getName(),
                            "Names should be the same");
                    assertEquals(designer.getSurname(), designerService.getOne(designer.getId()).getSurname(),
                            "Surnames should be the same");
                    assertEquals(designer.getDescription(), designerService.getOne(designer.getId()).getDescription(),
                            "Descriptions should be the same");
                    assertEquals(designer.getPhotoName(), designerService.getOne(designer.getId()).getPhotoName(),
                            "Photo names should be the same");
                }
        );
    }

    @Test
    @DisplayName("test deleteDesigner")
    @Transactional
    void deleteDesigner() {
        assertEquals(0, designerService.findAll().size(), "Should be zero");
        Designer designer = new Designer();
        designerRepository.save(designer);
        assertEquals(1, designerService.findAll().size(), "One element should be saved");
        designerService.deleteDesigner(designer.getId());
        assertEquals(0, designerService.findAll().size(), "Should be zero");
    }

    @Test
    void deletePic() {

    }

    @Test
    @DisplayName("test getPicNameByDesignerId")
    @Transactional
    void gePicNameByDesignerId() {
        Designer designer = new Designer();
        designer.setPhotoName("photo-name-for-test");
        designerRepository.save(designer);
        assertEquals(designer.getPhotoName(), designerService.gePicNameByDesignerId(designer.getId()),
                "Photo names should be the same");
    }

    @Test
    @DisplayName("test addPic")
    @Transactional
    void addPic() throws IOException {
        Designer designer = new Designer();
        designerService.save(designer);
        MockMultipartFile jpgFile =
                new MockMultipartFile("image", "1", "image/jpg", "someValue".getBytes());
        designerService.addPic(designerService.getOne(designer.getId()), jpgFile.getOriginalFilename(), jpgFile);
        assertEquals(jpgFile.getOriginalFilename(), designerService.getOne(designer.getId()).getPhotoName(),
                "Photos should be the same");
        Path path = Paths.get("src/main/webapp/resources/images/"+designerService.getOne(designer.getId()).getPhotoName());
        assertEquals(Arrays.toString(jpgFile.getBytes()), Arrays.toString(Files.readAllBytes(path)),
                "Photos should be the same");
        designerService.deletePic(designerService.getOne(designer.getId()).getPhotoName());
        designerService.deleteDesigner(designer.getId());
    }

    @Test
    void clearPic() {
    }
}