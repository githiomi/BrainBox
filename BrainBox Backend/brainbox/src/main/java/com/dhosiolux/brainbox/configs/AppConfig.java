package com.dhosiolux.brainbox.configs;

import com.dhosiolux.brainbox.controllers.EventController;
import com.dhosiolux.brainbox.models.ColorCodes;
import com.dhosiolux.brainbox.services.EventService;
import com.dhosiolux.brainbox.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Random;

@Configuration
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Bean
    CommandLineRunner commandLineRunner(EventService eventService, UserService userService){
        return args -> {
            logger.info(ColorCodes.BLUE + "Server started successfully at: " + LocalDateTime.now()  + ColorCodes.RESET);

            // Create dummy data
            userService.addTestUsers();
            eventService.addTestEvents();
        };
    }

    @Bean
    Random randomGenerator() {
        return new Random();
    }
}
