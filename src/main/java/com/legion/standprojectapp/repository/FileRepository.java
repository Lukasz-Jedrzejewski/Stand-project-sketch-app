package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
