package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.repository.DesignerRepository;
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
class DesignerServiceImplTest {

    @Autowired
    private DesignerServiceImpl designerService;

    @Autowired
    private DesignerRepository designerRepository;

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
    void save() {
    }

    @Test
    void edit() {
    }

    @Test
    void deleteDesigner() {
    }

    @Test
    void deletePic() {
    }

    @Test
    void gePicNameByDesignerId() {
    }

    @Test
    void addPic() {
    }

    @Test
    void clearPic() {
    }
}