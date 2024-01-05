package com.dhosiolux.brainbox;

import com.dhosiolux.brainbox.controllers.EventController;
import com.dhosiolux.brainbox.models.ColorCodes;
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
    CommandLineRunner commandLineRunner(ApplicationContext applicationContext){
        return args -> {
            logger.info(ColorCodes.BLUE + "Server started successfully at: " + LocalDateTime.now()  + ColorCodes.RESET);
        };
    }
}
