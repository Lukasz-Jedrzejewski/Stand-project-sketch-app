package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Photography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographyRepository extends JpaRepository<Photography, Long> {
}
