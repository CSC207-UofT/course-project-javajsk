package com.javajsk.uoftruck;

import adapters.dam.entityrepoitories.VendorDB;
import businessrules.dai.VendorRepository;
import com.mongodb.Mongo;
import framework.JWTSigner;
import framework.MongoDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})public class UoftruckApplication {
    //MongoDB db = new MongoDB();
    //public VendorRepository vendorrepo = new VendorDB(db);
    public static void main(String[] args) {



        SpringApplication.run(UoftruckApplication.class, args);
    }

}
