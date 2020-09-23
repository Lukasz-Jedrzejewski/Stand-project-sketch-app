package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.model.CurrentUser;

public interface UserService {
    boolean checkAdmin();
    void save(User user);
    User findByCompanyMail(String companyMail);
    boolean checkRole(long id);
    boolean existByMail(String companyMail);
    void changePassword(CurrentUser currentUser, User user);
    void editUser(User user);
    void resetPassword(String email, String password);
}
