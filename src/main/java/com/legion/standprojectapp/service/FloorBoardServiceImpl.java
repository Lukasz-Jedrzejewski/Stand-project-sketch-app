package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.FloorBoard;
import com.legion.standprojectapp.interfaces.FloorBoardService;
import com.legion.standprojectapp.repository.FloorBoarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorBoardServiceImpl implements FloorBoardService {
    private FloorBoarRepository floorBoarRepository;

    public FloorBoardServiceImpl(FloorBoarRepository floorBoarRepository) {
        this.floorBoarRepository = floorBoarRepository;
    }

    @Override
    public List<FloorBoard> findAll() {
        return floorBoarRepository.findAll();
    }

    @Override
    public FloorBoard getOne(long id) {
        return floorBoarRepository.getOne(id);
    }

    @Override
    public void save(FloorBoard floorBoard){
        boolean existFloorBoard = existsByName(floorBoard.getName());
        if (!existFloorBoard) {
            this.floorBoarRepository.save(floorBoard);
        }
    }

    @Override
    public void delete(long id) {
        this.floorBoarRepository.delete(getOne(id));
    }

    @Override
    public boolean existsByName(String name) {
        return floorBoarRepository.existsFloorBoardByName(name);
    }
}
