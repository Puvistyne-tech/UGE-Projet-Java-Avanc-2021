package com.friday.fridayback.controller;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.util.ICalDate;
import com.friday.fridayback.entity.Event;
import com.friday.fridayback.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping(path = "/ical")
public class ICalController {
    private final EventRepository eventRepository;

    @Autowired
    public ICalController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping("/uploadIcs")
    @ResponseBody
    public ResponseEntity<Object> readIcsFromFile(@RequestBody MultipartFile file) {
        try {
            Biweekly.parse(new String(file.getBytes(), StandardCharsets.UTF_8)).all().forEach(this::parseIcs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/IcsUrl")
    @ResponseBody
    public ResponseEntity<Object> readIcsFromUrl(@RequestParam("url") String url) {
        try {
            InputStream is = new URL(url).openStream();
            Biweekly.parse(is).all().forEach(this::parseIcs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void parseIcs(ICalendar ics) {
        ics.getEvents().forEach(this::addEvent);
    }

    private void addEvent(VEvent event) {
        try {
            Event toAdd = new Event();
            toAdd.setTitle(event.getSummary().getValue().trim());
            toAdd.setDescription(event.getDescription().getValue().trim());
            toAdd.setLocation(event.getLocation().getValue());
            toAdd.setStart(convertICalDateToLocalDateTime(event.getDateStart().getValue()));
            toAdd.setEnd(convertICalDateToLocalDateTime(event.getDateEnd().getValue()));
            eventRepository.save(toAdd);
        } catch (Exception e) {//les evenements pas bon ne doivent pas perturber l'application
        }
    }

    private LocalDateTime convertICalDateToLocalDateTime(ICalDate iCal) {
        System.out.println(LocalDate.ofEpochDay(iCal.getTime()/(1000 * 60 * 60 * 24)));
        return LocalDateTime.ofEpochSecond(iCal.getTime() / 1000, 0, ZoneOffset.of("+01:00"));
    }
}
