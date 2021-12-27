package com.friday.fridayback.controller;

import com.friday.fridayback.entity.Event;
import com.friday.fridayback.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/events")
public class EventController {
    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/event")
    @ResponseBody
    public ResponseEntity<List<Event>> getAllEvents() {
        try{
            List<Event> list = eventRepository.findAll();
            if(list.isEmpty() || list.size()==0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
/*        return ResponseHandler.generateResponse(
                "All Events",
                HttpStatus.OK,
                eventService.getAllEvent()
        );*/
    }

    @GetMapping(path = "/{date}")
    @ResponseBody
    public ResponseEntity<Object> getEventByDate(@PathVariable("date") @DateTimeFormat(pattern = "ddMMyyyy") Date date) {
        try{
            List<Event> list = eventRepository.findEventByDate(date);
            if(list.isEmpty() || list.size()==0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
/*        return ResponseHandler.generateResponse(
                "Event on " + date.toString(),
                HttpStatus.OK,
                eventService.getEventByDate(date)
        );*/
    }

    @PostMapping("/event")
    @ResponseBody
    public ResponseEntity<Event> setEvent(@RequestBody Event event) {
        try {
            return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /*        eventService.setEvent(event);
        return ResponseHandler.generateResponse(
                "Event created successfully",
                HttpStatus.OK,
                eventService.getEventByName(event.name())
        );*/
    }

    @PutMapping("/event")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        try {
            return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
/*        eventService.updateEvent(event);
        return ResponseHandler.generateResponse(
                "Event " + event.getName() + " is successfully updated",
                HttpStatus.OK,
                eventService.getEventById(event.getId())
        );*/
    }

    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Event> deleteEventById(@PathVariable Long eventId) {
        try {
            Optional<Event> customer = eventRepository.findById(eventId);
            if (customer.isPresent()) {
                eventRepository.delete(customer.get());
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
/*        var tmp = eventService.getEventById(eventId);
        eventService.deleteEventById(eventId);
        return ResponseHandler.generateResponse(
                "Event " + tmp.getName() + " deleted successfully",
                HttpStatus.OK,
                ""
        );*/
    }
}
