package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventInterface {

    List<Event> getEvents();

    Event getEventById(UUID eventId);

    Event addNewEvent(Event event);

    Event updateEventById(Event event);

    boolean deleteEventById(UUID eventId);


}
