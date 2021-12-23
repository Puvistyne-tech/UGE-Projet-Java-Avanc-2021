package com.friday.fridayback.event;

import java.sql.Time;
import java.util.Date;

public record EventCreator(String name,
                           String description,
                           String location,
                           Date startDate,
                           Date endDate,
                           Time startTime,
                           Time endTime) {
}
//TODO il faut creaer un builder pour l'EVENT. C'est mieux.