package com.javajsk.uoftruck;

import framework.MongoDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the app
 */
@Configuration
public class AppConfig {
    public @Bean
    MongoDB mongoDb() {
        return new MongoDB();
    }
}
