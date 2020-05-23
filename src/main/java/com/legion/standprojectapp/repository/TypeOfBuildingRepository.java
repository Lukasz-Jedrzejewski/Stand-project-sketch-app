package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.TypeOfBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfBuildingRepository extends JpaRepository<TypeOfBuilding, Long> {

}
