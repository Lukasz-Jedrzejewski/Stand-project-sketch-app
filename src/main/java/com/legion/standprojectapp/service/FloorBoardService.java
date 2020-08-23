package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.FloorBoard;

import java.util.List;

public interface FloorBoardService {
    List<FloorBoard> findAll();
    FloorBoard getOne(long id);
    void save(FloorBoard floorBoard);
    void delete(long id);
    boolean existsByName(String name);
}
