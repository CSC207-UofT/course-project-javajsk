package com.javajsk.uoftruck;

import entities.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


public class TestController {

    @PutMapping("/teasdasst/{test_string}")
    public Addon test(@PathVariable String test_string, @RequestBody Addon addon1){
        Addon[] temp = new Addon[2];
        List<Integer> type = new ArrayList<>();
        type.add(1);
        type.add(2);
        addon1.setName(test_string);
        Addon addon = new Addon("29382938","Sprinkle", (float) 59.3, type,false,"shopid" );
        Menu menu = new Menu();
        OrderBook orderBook = new OrderBook();
        Shop shop = new Shop(test_string, "Green Food Truck", "Bahen", true, menu, orderBook);
        Vendor vendor = new Vendor(test_string, "username", "strognpassword", shop);
        return addon1;
    }
}