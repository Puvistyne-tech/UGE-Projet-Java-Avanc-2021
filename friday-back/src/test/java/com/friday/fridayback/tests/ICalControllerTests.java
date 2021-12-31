package com.friday.fridayback.tests;

import com.friday.fridayback.controller.ICalController;
import com.friday.fridayback.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ICalControllerTests {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void failOnWrongUrlTest() {
        ICalController icc = new ICalController(eventRepository);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,icc.readIcsFromUrl("wrongUrl").getStatusCode());
    }
}
