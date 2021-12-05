package com.javajsk.uoftruck;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public MongoClient mongoClient() {
        String password = "F9PYZ6nevnvxGP6U";
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Application:" + password +
                "@cluster0." +
                "whkvw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }
}
