package com.friday.fridayback.controller;

import com.friday.fridayback.entity.Event;
import com.friday.fridayback.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/event/{id}")
    @ResponseBody
    public ResponseEntity<Event> getEventById(@PathVariable("id") long id) {
        try{
            Optional<Event> list = eventRepository.findById(id);
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list.get(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/findByDate")
    @ResponseBody
    public ResponseEntity<Object> getEventByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try{
            List<Event> list = eventRepository.findEventByStartDate(date.atStartOfDay(),date.plusDays(1).atStartOfDay());
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/event")
    @ResponseBody
    public ResponseEntity<Event> setEvent(@RequestBody Event event) {
        try {
            return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/event")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        try {
            return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Event> deleteEventById(@PathVariable Long eventId) {
        try {
            Optional<Event> customer = eventRepository.findById(eventId);
            customer.ifPresent(eventRepository::delete);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
