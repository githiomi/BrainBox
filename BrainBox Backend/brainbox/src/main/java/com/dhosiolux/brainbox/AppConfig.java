package com.dhosiolux.brainbox;

import com.dhosiolux.brainbox.controllers.EventController;
import com.dhosiolux.brainbox.enums.EventCategory;
import com.dhosiolux.brainbox.models.ColorCodes;
import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Bean
    CommandLineRunner commandLineRunner(EventService eventService){
        return args -> {
            logger.info(ColorCodes.BLUE + "Server started successfully at: " + LocalDateTime.now()  + ColorCodes.RESET);

            // Create at least 2 events to test
            Event graduation = new Event("2025 Graduation", EventCategory.CAMPUS_EVENT);
            Event awareness = new Event("Brain Cancer Awareness", EventCategory.HEALTH_AND_WELLNESS);

            eventService.addNewEvent(graduation);
            eventService.addNewEvent(awareness);

        };
    }
}
