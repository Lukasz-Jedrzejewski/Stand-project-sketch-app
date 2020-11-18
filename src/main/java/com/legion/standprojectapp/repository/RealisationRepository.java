package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Realisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisationRepository extends JpaRepository<Realisation, Long> {
}
