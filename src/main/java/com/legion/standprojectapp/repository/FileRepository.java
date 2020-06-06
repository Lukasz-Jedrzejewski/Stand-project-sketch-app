package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Integer> {
    List<File> findAllByProjectId(long id);
}
