package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.interfaces.EventInterface;
import com.dhosiolux.brainbox.models.Event;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService implements EventInterface {
    @Override
    public Event[] getEvents() {
        return new Event[0];
    }

    @Override
    public Event getEventById(UUID eventId) {
        return null;
    }

    @Override
    public Event addNewEvent(String eventTitle) {
        System.out.println("Creating new event");
        return new Event( eventTitle);
    }

    @Override
    public Event updateEventById(Event event) {
        return null;
    }

    @Override
    public boolean deleteEventById(UUID eventId) {
        return false;
    }
}
