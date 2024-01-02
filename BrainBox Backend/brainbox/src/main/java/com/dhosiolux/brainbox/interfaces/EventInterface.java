package com.dhosiolux.brainbox.interfaces;

import com.dhosiolux.brainbox.models.Event;

import java.util.UUID;

public interface EventInterface {

    Event[] getEvents();

    Event getEventById(UUID eventId);

    Event addNewEvent(String eventTitle);

    Event updateEventById(Event event);

    boolean deleteEventById(UUID eventId);


}
