package com.friday.fridayback.tests;

import com.friday.fridayback.controller.EventController;
import com.friday.fridayback.controller.ICalController;
import com.friday.fridayback.entity.Event;
import com.friday.fridayback.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
public class EventControllerTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void goodSizeBodyListGetAllTest() {
        EventController ec = new EventController(eventRepository);
        Event toAdd = new Event();
        toAdd.setTitle("title");
        toAdd.setDescription("desc");
        toAdd.setLocation("location");
        toAdd.setStart(LocalDateTime.now());
        toAdd.setEnd(LocalDateTime.now());
        eventRepository.save(toAdd);
        Assertions.assertEquals(1, ec.getAllEvents().getBody().size());
    }

    @Test
    public void goodEventByIdTest() {
        eventRepository.deleteAll();
        EventController ec = new EventController(eventRepository);
        Event toAdd = new Event();
        toAdd.setTitle("goodEventByIdTest");
        toAdd.setDescription("desc");
        toAdd.setLocation("location");
        toAdd.setStart(LocalDateTime.now());
        toAdd.setEnd(LocalDateTime.now());
        eventRepository.save(toAdd);
        Assertions.assertEquals(toAdd.getTitle(), ec.getEventById(2).getBody().getTitle());
    }

    @Test
    public void failOnWrongUrlTest() {
        eventRepository.deleteAll();
        EventController ec = new EventController(eventRepository);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,ec.getEventById(2).getStatusCode());
    }
}
