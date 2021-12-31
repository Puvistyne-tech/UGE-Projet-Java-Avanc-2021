package com.friday.fridayback.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Event {

    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @Column(name = "allDay")
    private boolean allDay;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.title = Objects.requireNonNull(title);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if(location.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.location = Objects.requireNonNull(location);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.description = Objects.requireNonNull(description);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime endDate) {
        this.end = Objects.requireNonNull(endDate);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime startDate) {
        this.start = Objects.requireNonNull(startDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getAllDay() {return allDay;}

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
}
