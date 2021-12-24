package com.friday.fridayback.event;

import com.friday.fridayback.handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getAllEvents() {
        return ResponseHandler.generateResponse(
                "All Events",
                HttpStatus.OK,
                eventService.getAllEvent()
        );
    }

    @GetMapping(path = "{date}")
    @ResponseBody
    public ResponseEntity<Object> getEventByDate(@PathVariable("date") @DateTimeFormat(pattern = "ddMMyyyy") Date date) {
        return ResponseHandler.generateResponse(
                "Event on " + date.toString(),
                HttpStatus.OK,
                eventService.getEventByDate(date)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> setEvent(@RequestBody EventCreator event) {
        eventService.setEvent(event);
        return ResponseHandler.generateResponse(
                "Event created successfully",
                HttpStatus.OK,
                eventService.getEventByName(event.name())
        );
    }

    @PutMapping
    public ResponseEntity<Object> updateEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
        return ResponseHandler.generateResponse(
                "Event " + event.getName() + " is successfully updated",
                HttpStatus.OK,
                eventService.getEventById(event.getId())
        );
    }

    @DeleteMapping(path = "{eventId}")
    public ResponseEntity<Object> deleteEventById(@PathVariable Long eventId) {
        var tmp = eventService.getEventById(eventId);
        eventService.deleteEventById(eventId);
        return ResponseHandler.generateResponse(
                "Event " + tmp.getName() + " deleted successfully",
                HttpStatus.OK,
                ""
        );
    }
}
