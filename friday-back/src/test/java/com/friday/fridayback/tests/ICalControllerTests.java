package com.friday.fridayback.tests;

import com.friday.fridayback.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ICalControllerTests {
    @Autowired
    private EventRepository eventRepository;
}
