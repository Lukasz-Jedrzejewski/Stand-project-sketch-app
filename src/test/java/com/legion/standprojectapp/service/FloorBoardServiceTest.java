package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.FloorBoard;
import com.legion.standprojectapp.repository.FloorBoarRepository;
import com.legion.standprojectapp.service.serviceImpl.FloorBoardServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
class FloorBoardServiceTest {

    @InjectMocks
    FloorBoardServiceImpl floorBoardService;

    @Mock
    FloorBoarRepository floorBoardRepository;

    private static final long ID = 1;

    @Before
    public void setUp(){
        this.floorBoardService = new FloorBoardServiceImpl(floorBoardRepository);

    }

    @Test
    void findAll() {
        List<FloorBoard> floorBoards = floorBoardService.findAll();
        assertNotNull(floorBoards);
    }

    @Test
    void getOne() {
        floorBoardService.getOne(ID);
        verify(floorBoardRepository).getOne(ID);
    }

    @Test
    void save() {
        FloorBoard floorBoard = mock(FloorBoard.class);
        floorBoardService.save(floorBoard);
        verify(floorBoardRepository).save(floorBoard);

    }

    @Test
    void delete() {
        floorBoardService.delete(ID);
        verify(floorBoardRepository).delete(floorBoardService.getOne(ID));

    }
}