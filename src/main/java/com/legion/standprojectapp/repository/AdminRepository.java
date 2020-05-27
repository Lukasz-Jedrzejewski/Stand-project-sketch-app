package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Admin;
import com.legion.standprojectapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
