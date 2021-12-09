package com.javajsk.uoftruck;

import io.jsonwebtoken.io.IOException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class UoftruckApplication {
    public static void main(String[] args) {
        SpringApplication.run(UoftruckApplication.class, args);



        }


}
