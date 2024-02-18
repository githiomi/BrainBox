package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventInterface {

    List<Event> getEvents();

    Event getEventById(UUID eventId);

    List<Event> getEventsByUsername(String username);

    List<Event> getEventsAfterGivenDate(LocalDate date);

    Event addNewEvent(Event event);

    Event updateEventById(Event event);

    boolean deleteEventById(UUID eventId);

}
