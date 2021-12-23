package com.friday.fridayback.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository
        extends JpaRepository<Event, Long> {

    @Query("select d from Event d where d.startDate <= ?1 and d.endDate >=?1")
    List<Event> findEventByDate(Date date);

//    List<Event> findEventsByStartDateIsGreaterThanEqualAndAndEndDateLessThanEqual(Date date);

    Optional<Event> findByName(String name);

//    public List<Event> findAllByEndDateOrStartDate(Date date);

//    @Modifying
//    @Query("update Event u se")
//    public void updateEvent()
}