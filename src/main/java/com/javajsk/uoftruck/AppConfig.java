package com.javajsk.uoftruck;

import adapters.dam.TokenSigner;
import com.mongodb.Mongo;
import framework.JWTSigner;
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
