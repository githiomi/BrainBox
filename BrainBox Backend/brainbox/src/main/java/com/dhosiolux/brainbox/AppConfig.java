package com.dhosiolux.brainbox;

import com.dhosiolux.brainbox.controllers.EventController;
import com.dhosiolux.brainbox.enums.EventCategory;
import com.dhosiolux.brainbox.models.ColorCodes;
import com.dhosiolux.brainbox.models.Event;
import com.dhosiolux.brainbox.models.Role;
import com.dhosiolux.brainbox.models.User;
import com.dhosiolux.brainbox.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dhosiolux.brainbox.models.Role.ADMIN;
import static com.dhosiolux.brainbox.models.Role.ALUMNI;

@Configuration
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Bean
    CommandLineRunner commandLineRunner(EventService eventService){
        return args -> {
            logger.info(ColorCodes.BLUE + "Server started successfully at: " + LocalDateTime.now()  + ColorCodes.RESET);

            // Create at least 2 events to test
            eventService.addNewEvent(new Event("2025 Graduation", EventCategory.CAMPUS_EVENT));
            eventService.addNewEvent(new Event("Brain Cancer Awareness", EventCategory.HEALTH_AND_WELLNESS));

            List<User> usersList = new ArrayList<>(
                    Arrays.asList(
                            new User("Daniel", "Githiomi", ADMIN),
                            new User("MacDonald", "Nyahoja", ALUMNI),
                            new User("Nabila", "Modan", ALUMNI)
                    )
            );

            usersList.forEach(System.out::println);
        };
    }
}
