package com.legion.standprojectapp.interfaces;

import com.legion.standprojectapp.entity.User;

public interface UserService {
    boolean checkAdmin();
    void save(User user);
    User findByCompanyMail(String companyMail);

}
