package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.admin = true")
    List<User> findByAdmin();

    User findByCompanyMail(String companyMail);

    boolean existsUserByCompanyMailLike(String companyMail);
    boolean existsUserByCompanyMailStartingWith(String companyMail);
    boolean existsUserByCompanyMailEndingWith(String companyMail);
    boolean existsUserByCompanyMailContaining(String companyMail);

    User findByAdminTrue();

}
