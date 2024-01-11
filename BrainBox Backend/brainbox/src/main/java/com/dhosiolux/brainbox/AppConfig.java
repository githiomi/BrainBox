package com.dhosiolux.brainbox;

import com.dhosiolux.brainbox.controllers.EventController;
import com.dhosiolux.brainbox.enums.EventCategory;
import com.dhosiolux.brainbox.models.ColorCodes;
import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.models.User;
import com.dhosiolux.brainbox.services.EventService;
import com.dhosiolux.brainbox.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dhosiolux.brainbox.enums.Role.ADMIN;
import static com.dhosiolux.brainbox.enums.Role.ALUMNI;

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
}
