package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.enums.EventCategory;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.interfaces.EventInterface;
import com.dhosiolux.brainbox.models.Event;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EventService implements EventInterface {

    private List<Event> events = new ArrayList<>();

    public void addTestEvents() {
        this.events.addAll(Arrays.asList(
                        new Event("2025 Graduation", EventCategory.CAMPUS_EVENT),
                        new Event("Brain Cancer Awareness", EventCategory.HEALTH_AND_WELLNESS)
                )
        );
    }

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
