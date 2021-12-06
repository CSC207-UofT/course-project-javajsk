package com.javajsk.uoftruck;

import adapters.dam.entityrepoitories.AddonDB;
import adapters.dam.entityrepoitories.SingletonDB;
import com.mongodb.util.JSON;
import entities.Addon;
import entities.Selection;
import entities.Singleton;
import framework.JWTSigner;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class UoftruckApplication {
    public static void main(String[] args) {

        SpringApplication.run(UoftruckApplication.class, args);
    }

}
