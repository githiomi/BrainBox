package com.dhosiolux.brainbox.controllers;

import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.models.ResourceResponse;
import com.dhosiolux.brainbox.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1.0/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("")
    private ResponseEntity<ResourceResponse<List<Event>>> getAllEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResourceResponse<List<Event>>("All events have been retrieved from the database", this.eventService.getEvents(), LocalDateTime.now() ));
    }

    @GetMapping( "/{id}")
    private ResponseEntity<ResourceResponse<Event>> getEventById(@PathVariable("id") UUID eventId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResourceResponse<Event>("We have retrieved one event by its ID", this.eventService.getEventById(eventId), LocalDateTime.now()));
    }

    @PostMapping("/create")
    private ResponseEntity<ResourceResponse<Event>> createNewEvent(@RequestBody Event newEvent) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceResponse<Event>("The event has been created successfully.", this.eventService.addNewEvent(newEvent), LocalDateTime.now()));
    }

    @PutMapping("/update")
    private ResponseEntity<ResourceResponse<Event>> updateExistingEvent(@RequestBody Event event){
        return ResponseEntity.status(HttpStatus.OK).body(new ResourceResponse<Event>("The event with id: " + event.getEventId() + " has been updated successfully", this.eventService.updateEventById(event), LocalDateTime.now()));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ResourceResponse<Boolean>> deleteExistingEvent(@PathVariable("id") UUID eventId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResourceResponse<Boolean>("The event with id: " + eventId + " has been successfully deleted", this.eventService.deleteEventById(eventId), LocalDateTime.now()));
    }

}
