package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {

    @Query(value = "select photoName from Designer d where d.id = :id")
    String getPhotoName (@Param("id") long id);
}
