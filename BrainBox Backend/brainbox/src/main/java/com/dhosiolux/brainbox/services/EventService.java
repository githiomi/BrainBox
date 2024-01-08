package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.interfaces.EventInterface;
import com.dhosiolux.brainbox.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EventService implements EventInterface {

    private final List<Event> events = new ArrayList<>();

    @Override
    public List<Event> getEvents() {
        return events.reversed();
    }

    @Override
    public Event getEventById(UUID eventId) {
        return this.events.stream().filter(_event -> _event.getEventId().equals(eventId)).findFirst().orElseThrow(() -> new ResourceNotFoundException("No event with the id: " + eventId + " was found on the database.", NOT_FOUND));
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
                .orElseThrow(() -> new ResourceNotFoundException("No event with the id: " + event.getEventId() + " was found on the database.", NOT_FOUND));
        eventToUpdate.setEventName(event.getEventName());
        return eventToUpdate;
    }

    @Override
    public boolean deleteEventById(UUID eventId) {
        Event eventToDelete = this.events.stream().filter(_event -> _event.getEventId().equals(eventId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No event with the id: " + eventId + " was found on the database.", NOT_FOUND));
        return this.events.remove(eventToDelete);
    }
}
