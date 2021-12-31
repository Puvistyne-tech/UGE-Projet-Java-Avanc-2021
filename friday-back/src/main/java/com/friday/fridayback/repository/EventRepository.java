package com.friday.fridayback.repository;

import com.friday.fridayback.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository
        extends JpaRepository<Event, Long> {

    @Query("select d from Event d where d.start >= ?1 and d.start < ?2")
    List<Event> findEventByStartDate(LocalDateTime startDate, LocalDateTime endDate);

}