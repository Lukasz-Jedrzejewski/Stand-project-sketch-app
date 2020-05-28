package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.admin = true")
    List<User> findByAdmin();

    User findByCompanyMail(String companyMail);

}
