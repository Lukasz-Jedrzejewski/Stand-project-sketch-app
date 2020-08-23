package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.repository.CurrentEventRepository;
import com.legion.standprojectapp.service.serviceImpl.CurrentEventServiceImpl;
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
class CurrentEventServiceImplTest {

    @InjectMocks
    CurrentEventServiceImpl currentEventService;

    @Mock
    CurrentEventRepository currentEventRepository;

    private static final long ID = 1;

    @Before
    public void setUp(){
        this.currentEventService = new CurrentEventServiceImpl(currentEventRepository);

    }

    @Test
    void findAll() {
        List<CurrentEvent> currentEvents = currentEventService.findAll();
        assertNotNull(currentEvents);
    }

    @Test
    void getOne() {
        currentEventService.getOne(ID);
        verify(currentEventRepository).getOne(ID);
    }

    @Test
    void save() {
        CurrentEvent currentEvent = mock(CurrentEvent.class);
        currentEventService.save(currentEvent);
        verify(currentEventRepository).save(currentEvent);

    }

    @Test
    void delete() {
        currentEventService.delete(ID);
        verify(currentEventRepository).delete(currentEventService.getOne(ID));

    }
}