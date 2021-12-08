package com.javajsk.uoftruck;

import framework.MongoDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public @Bean
    MongoDB mongoDb(){
        return new MongoDB();
    }
}
