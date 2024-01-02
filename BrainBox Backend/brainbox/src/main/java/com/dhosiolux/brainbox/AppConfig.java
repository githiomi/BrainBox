package com.dhosiolux.brainbox;

import com.dhosiolux.brainbox.models.ColorCodes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext applicationContext){
        return args -> {
            System.out.println(ColorCodes.BLUE + "\nServer started successfully at: " + LocalDateTime.now() + "/n" + ColorCodes.RESET);
        };
    }
}
