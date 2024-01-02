package com.dhosiolux.brainbox;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext applicationContext){
        return args -> {
            String[] beans = applicationContext.getBeanDefinitionNames();
            Arrays.sort(beans);
        };
    }
}
