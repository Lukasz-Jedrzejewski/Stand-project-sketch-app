package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Photography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PhotographyRepository extends JpaRepository<Photography, Integer> {
    @Transactional
    void deleteByDesignerId(long id);

    Photography findByDesignerId(long id);

    boolean existsPhotographyByDesignerId(long id);
}
