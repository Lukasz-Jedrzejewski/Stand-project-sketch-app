package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Long> {

    @Query(value = "select logoName from CompanyInfo c where c.id = :id")
    String getLogoName (@Param("id") long id);
}
