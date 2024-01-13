package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.enums.EventCategory;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.interfaces.EventInterface;
import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.models.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EventService implements EventInterface {

    // Service Dependency Injection
    private final UserService userService;

    private List<Event> events;

    public EventService(UserService userService) {
        this.userService = userService;
    }

    public void addTestEvents() {
        this.events = new ArrayList<>(
                Arrays.asList(
                        new Event("2025 Graduation", EventCategory.CAMPUS_EVENT, ""),
                        new Event("Brain Cancer Awareness", EventCategory.HEALTH_AND_WELLNESS, "")
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
        newEvent.setEventCategory(Enum.valueOf(EventCategory.class, newEvent.getEventCategory().toString()));
        this.events.add(newEvent);
        return newEvent;
    }

    @Override
    public List<Event> getEventsByUsername(String username) {
        // Safe first - ensure user exists
        User user = this.userService.getUserByUsername(username);

        return this.events.stream().filter(_event -> _event.getCreatedBy().equals(username)).collect(Collectors.toList());
    }

    @Override
    public Event updateEventById(Event event) {

        // Verify that username corresponds to actual user
        this.userService.getUserByUsername(event.getCreatedBy());

        Event eventToUpdate = this.events.stream().filter(_event -> _event.getEventId().equals(event.getEventId())).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No event with the id: " + event.getEventId() + " was found on the database.", NOT_FOUND));

        // Get event index
        int index = this.events.indexOf(eventToUpdate);

        // Remove event from list
        this.events.remove(eventToUpdate);

        // Update event fields
        eventToUpdate.setEventName(event.getEventName());
        eventToUpdate.setCreatedBy(event.getCreatedBy());

        // Add event back to list
        this.events.add(index, eventToUpdate);
        return eventToUpdate;
    }

    @Override
    public boolean deleteEventById(UUID eventId) {
        Event eventToDelete = this.events.stream().filter(_event -> _event.getEventId().equals(eventId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No event with the id: " + eventId + " was found on the database.", NOT_FOUND));
        return this.events.remove(eventToDelete);
    }
}
