package com.friday.fridayback.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository evenRepository;

    @Autowired
    public EventService(EventRepository evenRepository) {
        this.evenRepository = evenRepository;
    }


    public List<Event> getAllEvent() {
        return evenRepository.findAll();
    }

    public Event getEventById(Long id) {
        return evenRepository.getById(id);
    }

    public List<Event> getEventByDate(Date date) {
//        return evenRepository.findEventByDate(date);
        return evenRepository.findEventByDate(date);
    }

    public Event getEventByName(String name) {
        var eventOptional = evenRepository.findByName(name);
        if (eventOptional.isPresent()) return eventOptional.get();
        else throw new IllegalArgumentException("Event does not exist");
    }

    public void setEvent(EventCreator event) {
        Optional<Event> eventOptional = evenRepository.findByName(event.name());
        if (eventOptional.isPresent()) {
            throw new IllegalStateException("Event already exists");
        }
        evenRepository.save(new Event(
                event.name(),
                event.description(),
                event.location(),
                event.startDate(),
                event.endDate(),
                event.startTime(),
                event.endTime()
        ));
    }

    @Transactional
    public void updateEvent(Event event) {
//        if (evenRepository.existsById(event.getId())) {
//            //TODO
//            evenRepository.delete(event);
//
//
//            evenRepository.save(event);
//        }
        Optional<Event> eventOptional = evenRepository.findById(event.getId());
        if (eventOptional.isPresent()) {
            //TODO

            var tmp = eventOptional.get();
            if (event.getName() != null)
                tmp.setName(event.getName());
            if (event.getDescription() != null)
                tmp.setDescription(event.getDescription());
            if (event.getLocation() != null)
                tmp.setLocation(event.getLocation());
            if (event.getStartDate() != null)
                tmp.setStartDate(event.getStartDate());
            if (event.getEndDate() != null)
                tmp.setEndDate(event.getEndDate());
            if (event.getStartTime() != null)
                tmp.setStartTime(event.getStartTime());
            if (event.getEndTime() != null)
                tmp.setEndTime(event.getEndTime());
            evenRepository.save(tmp);
        } else {
            throw new IllegalStateException("Event does not exists");
        }
    }

    public void deleteEventById(Long eventId) {
        Optional<Event> eventOptional = evenRepository.findById(eventId);
        if (eventOptional.isEmpty()) {
            throw new IllegalStateException("Event does not exists");
        }
        evenRepository.deleteById(eventId);
    }

}
