package com.friday.fridayback.tests;

import com.friday.fridayback.entity.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EventTests {

    @Test
    public void nullTitleEventTest() {
        Assertions.assertThrows(NullPointerException.class,() -> new Event().setTitle(null));
    }

    @Test
    public void nullDescEventTest() {
        Assertions.assertThrows(NullPointerException.class,() -> new Event().setDescription(null));
    }

    @Test
    public void nullLocationEventTest() {
        Assertions.assertThrows(NullPointerException.class,() -> new Event().setLocation(null));
    }

    @Test
    public void nullStartEventTest() {
        Assertions.assertThrows(NullPointerException.class,() -> new Event().setStart(null));
    }

    @Test
    public void nullEndEventTest() {
        Assertions.assertThrows(NullPointerException.class,() -> new Event().setEnd(null));
    }
}
