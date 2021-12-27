package com.friday.fridayback.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.friday.fridayback.event.repetition.Repetition;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
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
    private Long id;

//    @Column(name="rule")
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "")
//    private Repetition rule;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "ddMMyyyy")
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "ddMMyyyy")
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    //    TODO
//    repetitions
/*
    //TODO need to find a method to link a user to his event
    public Event(String name, String description, String location, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.location = Objects.requireNonNull(location);
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.startTime = Objects.requireNonNull(startTime);
        this.endTime = Objects.requireNonNull(endTime);
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = Objects.requireNonNull(endTime);
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = Objects.requireNonNull(startTime);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = Objects.requireNonNull(location);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = Objects.requireNonNull(endDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = Objects.requireNonNull(startDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
