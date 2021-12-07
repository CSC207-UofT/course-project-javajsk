package com.javajsk.uoftruck;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class UoftruckApplication {
    public static void main(String[] args) {
        //"{"61ad9ef391dcd72eb9ae1cbf":5,"61adb578789c5e1b2828e248":2}
        //JSONObject test = new JSONObject("{\"61ad9ef391dcd72eb9ae1cbf\":5,\"61adb578789c5e1b2828e248\":2}");
        ///System.out.println(test);
        SpringApplication.run(UoftruckApplication.class, args);

    }

}
