package com.legion.standprojectapp.repository;

import com.legion.standprojectapp.entity.FloorBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorBoarRepository extends JpaRepository<FloorBoard, Long> {
    boolean existsFloorBoardByName(String name);
}
