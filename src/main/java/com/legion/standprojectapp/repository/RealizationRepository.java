package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Realization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealizationRepository extends JpaRepository<Realization, Long> {

    List<Realization> findAllByImportantTrue();
}
