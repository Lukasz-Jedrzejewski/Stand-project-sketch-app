package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Project p set p.branch.id = null where p.branch.id = :id")
    int setBranchIdToNull(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "update Project p set p.floorBoard.id = null where p.floorBoard.id = :id")
    int setFloorBoardIdToNull(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "update Project p set p.typeOfBuilding.id = null where p.typeOfBuilding.id = :id")
    int setTypeOfBuildingIdToNull(@Param("id") long id);

    @Query("select p from Project p where p.companyMail like ?1%")
    List<Project> findAllByCompanyMailLike(@Param("companyMail") String companyMail);
}
