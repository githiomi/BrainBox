package com.dhosiolux.brainbox.services;

import com.dhosiolux.brainbox.enums.EventCategory;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.interfaces.EventInterface;
import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.models.UserEntity;
import com.dhosiolux.brainbox.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EventService implements EventInterface {

    // Service Dependency Injection
    private final UserService userService;
    private final EventRepository eventRepository;

    private List<Event> events;

    public EventService(UserService userService, EventRepository eventRepository) {
        this.userService = userService;
        this.eventRepository = eventRepository;
    }

    public void addTestEvents() {
        this.events = new ArrayList<>(
                Arrays.asList(
                        new Event("2025 Graduation", EventCategory.CAMPUS_EVENT, ""),
                        new Event("Brain Cancer Awareness", EventCategory.HEALTH_AND_WELLNESS, "")
                )
        );
        Event demoEvent1 = new Event("Daniel Githiomi Event", EventCategory.PERSONAL_DEVELOPMENT, "Daniel Githiomi", LocalDate.of(2020, 5, 12));
        this.eventRepository.save(demoEvent1);
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
    public List<Event> getEventsAfterGivenDate(LocalDate date) {
        return this.eventRepository.findEventByCreatedAtDateAfter(date);
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
        UserEntity userEntity = this.userService.getUserByUsername(username);

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

        // Add event back to list
        event.setUpdatedOn(LocalDateTime.now());
        this.events.add(index, event);
        return event;
    }

    @Override
    public boolean deleteEventById(UUID eventId) {
        Event eventToDelete = this.events.stream().filter(_event -> _event.getEventId().equals(eventId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No event with the id: " + eventId + " was found on the database.", NOT_FOUND));
        return this.events.remove(eventToDelete);
    }
}
