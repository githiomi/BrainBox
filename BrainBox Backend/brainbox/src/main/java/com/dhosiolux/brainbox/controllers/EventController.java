package com.dhosiolux.brainbox.controllers;

import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.models.ResourceResponse;
import com.dhosiolux.brainbox.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("api/v1.0/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("")
    private ResponseEntity<ResourceResponse<Event[]>> getAllEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResourceResponse<Event[]>("All events have been retrieved from the database", this.eventService.getEvents(), LocalDateTime.now() ));
    }

    @PostMapping("/create")
    private ResponseEntity<ResourceResponse<Event>> createNewEvent(@RequestBody String eventTitle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceResponse<Event>("The event has been created successfully.", this.eventService.addNewEvent(eventTitle), LocalDateTime.now()));
    }

    @PutMapping("/update")
    private ResponseEntity<ResourceResponse<Event>> updateExistingEvent(@RequestBody Event event){
        return ResponseEntity.status(HttpStatus.OK).body(new ResourceResponse<Event>("The event with id: " + event.eventId() + " has been updated successfully", this.eventService.updateEventById(event), LocalDateTime.now()));
    }

}
