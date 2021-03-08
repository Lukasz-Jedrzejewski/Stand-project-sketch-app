package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.StandProjectAppApplication;
import com.legion.standprojectapp.entity.Role;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.fixture.InitData;
import com.legion.standprojectapp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandProjectAppApplication.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InitData initData;

    User user = new User("testUser", "testUser@mail.com", "testUserPass");
    User admin = new User("testAdmin", "testAdmin@mail.com", "testAdminPass");

    @BeforeAll
    public void initRoles() {
        initData.initRoles();
    }

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("test checkAdmin")
    void checkAdminExist() {
        assertAll(
                () -> {
                    assertFalse(userService.checkAdminExist(), "should be false, nothing is saved");
                },
                () -> {
                    userService.save(admin);
                    assertTrue(userService.checkAdminExist(), "should be true, one element is saved");
                }
        );
    }

    @Test
    void save() {
        assertAll(
                () -> {
                    assertTrue(userRepository.findAll().isEmpty(), " should return true");
                },
                () -> {
                    userService.save(admin);
                    userService.save(user);
                    assertEquals(2, userRepository.findAll().size(), "Two elements should be saved");
                },
                () -> {
                    User currentAdmin = userRepository.findByAdminTrue();
                    assertEquals("ROLE_ADMIN", currentAdmin.getRoles().stream().map(Role::getName).findFirst().get(),
                            "should be the same");
                    User currentUser = userRepository.findByCompanyMail(user.getCompanyMail());
                    assertEquals("ROLE_USER", currentUser.getRoles().stream().map(Role::getName).findFirst().get(),
                            "should be the same");
                }
        );
    }

    @Test
    void editUser() {
    }

    @Test
    void testEditUser() {
    }

    @Test
    @DisplayName("test findByCompanyMail")
    void findByCompanyMail() {
        assertAll(
                () -> {
                    assertNull(userService.findByCompanyMail(user.getCompanyMail()), "nothing should be found");
                    assertNull(userService.findByCompanyMail(admin.getCompanyMail()), "nothing should be found");
                },
                () -> {
                    userService.save(user);
                    assertNotNull(userService.findByCompanyMail(user.getCompanyMail()), "user should be found");
                }
        );
    }

    @Test
    @DisplayName("test findById")
    void findById() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(NullPointerException.class, () -> userService.findById(user.getId()));
                    assertNull(exception.getMessage());
                },
                () -> {
                    userService.save(user);
                    assertNotNull(userService.findById(user.getId()));
                }
        );

    }

    @Test
    @Transactional
    @DisplayName("tet checkRole")
    void checkRole() {
        assertAll(
                () -> {
                    Exception exception = assertThrows(NullPointerException.class, () -> userService.findById(user.getId()));
                    assertNull(exception.getMessage());
                },
                () -> {
                    userService.save(admin);
                    userService.save(user);
                    assertTrue(userService.checkRole(admin.getId()), "should be true");
                    assertFalse(userService.checkRole(user.getId()), "should be false");
                }
        );
    }

    @Test
    @Transactional
    @DisplayName("test existByMail")
    void existByMail() {
        assertAll(
                () -> {
                    assertFalse(userService.existByMail(admin.getCompanyMail()), "admin should not exist");
                },
                () -> {
                    userService.save(admin);
                    assertEquals(admin.getCompanyMail(), userService.findById(admin.getId()).getCompanyMail(),
                            "should be the same");
                    assertTrue(userService.existByMail(admin.getCompanyMail()), "admin should exist");
                    assertTrue(userService.existByMail("test"), "admin should exist");
                    assertTrue(userService.existByMail("min@"), "admin should exist");
                    assertTrue(userService.existByMail("mail.com"), "admin should exist");
                }
        );
    }

    @Test
    void changePassword() {
    }

    @Test
    void resetPassword() {
    }
}