package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.interfaces.EventInterface;
import com.dhosiolux.brainbox.models.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService implements EventInterface {

    private final List<Event> events = new ArrayList<Event>();

    @Override
    public List<Event> getEvents() {
        return events.reversed();
    }

    @Override
    public Optional<Event> getEventById(UUID eventId) {
        return this.events.stream().filter(_event -> _event.getEventId().equals(eventId)).findFirst();
    }

    @Override
    public Event addNewEvent(Event newEvent) {
        newEvent.setEventId(UUID.randomUUID());
        this.events.add(newEvent);
        return newEvent;
    }

    @Override
    public Event updateEventById(Event event) {
        Event eventToUpdate = this.events.stream().filter(_event -> _event.getEventId().equals(event.getEventId())).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The event with id: " + event.getEventId() + " could not be found in the database."));
        eventToUpdate.setEventName(event.getEventName());
        return eventToUpdate;
    }

    @Override
    public boolean deleteEventById(UUID eventId) {
        Event eventToDelete = this.events.stream().filter(_event -> _event.getEventId().equals(eventId)).findFirst()
                .orElseThrow(() -> new RuntimeException("No Event was found with the id: " + eventId));
        return this.events.remove(eventToDelete);
    }
}
