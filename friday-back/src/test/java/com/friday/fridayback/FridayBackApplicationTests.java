package com.friday.fridayback;

import com.friday.fridayback.entity.Event;
import com.friday.fridayback.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class FridayBackApplicationTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(eventRepository);
    }

    @Test
    public void noEventAtBeginningTest() {
        List<Event> noEvent = eventRepository.findAll();
        Assertions.assertTrue(noEvent.isEmpty());
    }

    @Test
    public void emptyEventSaveTest() {
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> eventRepository.save(new Event()));
    }

    @Test
    public void emptyTitleEventSaveTest() {
        Event toAdd = new Event();
        toAdd.setDescription("desc");
        toAdd.setLocation("location");
        toAdd.setStart(LocalDateTime.now());
        toAdd.setEnd(LocalDateTime.now());
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> eventRepository.save(toAdd));
    }

    @Test
    public void emptyDescEventSaveTest() {
        Event toAdd = new Event();
        toAdd.setTitle("title");
        toAdd.setLocation("location");
        toAdd.setStart(LocalDateTime.now());
        toAdd.setEnd(LocalDateTime.now());
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> eventRepository.save(toAdd));
    }

    @Test
    public void emptyLocationEventSaveTest() {
        Event toAdd = new Event();
        toAdd.setTitle("title");
        toAdd.setDescription("desc");
        toAdd.setStart(LocalDateTime.now());
        toAdd.setEnd(LocalDateTime.now());
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> eventRepository.save(toAdd));
    }

    @Test
    public void emptyStartEventSaveTest() {
        Event toAdd = new Event();
        toAdd.setTitle("title");
        toAdd.setDescription("desc");
        toAdd.setLocation("location");
        toAdd.setEnd(LocalDateTime.now());
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> eventRepository.save(toAdd));
    }

    @Test
    public void emptyEndEventSaveTest() {
        Event toAdd = new Event();
        toAdd.setTitle("title");
        toAdd.setDescription("desc");
        toAdd.setLocation("location");
        toAdd.setStart(LocalDateTime.now());
        Assertions.assertThrows(DataIntegrityViolationException.class,() -> eventRepository.save(toAdd));
    }

}
