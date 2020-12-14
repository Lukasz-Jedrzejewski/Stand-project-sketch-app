package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandProjectAppApplication.class)
class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private RoleRepository roleRepository;

    Role userRole = new Role("ROLE_USER");
    Role adminRole = new Role("ROLE_ADMIN");
    Role modRole = new Role("ROLE_MOD");
    Role guestRole = new Role("ROLE_GUEST");

    @Test
    @DisplayName("test save")
    void save() {
        System.out.println(roleRepository.findAll().size() + " 111");
        assertAll(
                () -> {
                    assertEquals(2, roleRepository.findAll().size(),
                            "Two items are saved at application startup");
                },
                () -> {
                    roleService.save(userRole);
                    roleService.save(adminRole);
                    assertEquals(2, roleRepository.findAll().size(),
                            "These two elements already exist in the database");
                },
                () -> {
                    roleService.save(modRole);
                    roleService.save(guestRole);
                    assertEquals(4, roleRepository.findAll().size(),
                            "These two elements do not exist and should therefore be written down");
                    assertNotNull(roleRepository.getOne(modRole.getId()), "Should exist");
                    assertNotNull(roleRepository.getOne(guestRole.getId()), "Should exist");
                }
        );
    }

    @Test
    @DisplayName("test existByName")
    void existsByName() {
        assertAll(
                () -> {
                    assertTrue(roleService.existsByName(userRole.getName()),
                            "This item is saved on application startup");
                    assertTrue(roleService.existsByName(adminRole.getName()),
                            "This item is saved on application startup");
                    assertFalse(roleService.existsByName(modRole.getName()),
                            "This item is not saved");
                    assertFalse(roleService.existsByName(guestRole.getName()),
                            "This item is not saved");
                },
                () -> {
                    roleService.save(modRole);
                    roleService.save(guestRole);
                    assertTrue(roleService.existsByName(userRole.getName()),
                            "This item is saved on application startup");
                    assertTrue(roleService.existsByName(adminRole.getName()),
                            "This item is saved on application startup");
                    assertTrue(roleService.existsByName(modRole.getName()),
                            "This item should be saved");
                    assertTrue(roleService.existsByName(guestRole.getName()),
                            "This item should be saved");
                }
        );
    }
}