package com.javajsk.uoftruck;

import com.javajsk.uoftruck.controllers.SelectionController;
import entities.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestController {

    @PutMapping("/teasdasst/{test_string}")
    public Food test(@PathVariable String test_string){
        Addon[] temp = new Addon[2];
        List<Integer> type = new ArrayList<>();

        type.add(1);
        type.add(2);
        //addon1.setName(test_string);
        List<Integer> a = new ArrayList<Integer>();
        a.add(1);
        Addon addon = new Addon("addon_id", "addonname", 1, a, true, "shopId");
        HashMap<Addon, Integer> Singletonselection  = new HashMap<>();
        Singletonselection.put(addon, 1);
        Selection defaultselection = new Selection(Singletonselection);
        List<Integer> addon_types= new ArrayList<Integer>();
        addon_types.add(1);
        Singleton singleton = new Singleton("id", 20,"name",
                "description", addon_types, defaultselection, true, "aijd");
        Singleton[] singletons = new Singleton[1];
        singletons[0] = singleton;
        Food food = new Food("idid", "name", "description",20,singletons, "alkjds");
        //Addon addon = new Addon("29382938","Sprinkle", (float) 59.3, type,false,"shopid" );
//        Menu menu = new Menu();
//        OrderBook orderBook = new OrderBook();
//        Shop shop = new Shop(test_string, "Green Food Truck", "Bahen", true, menu, orderBook);
//        Vendor vendor = new Vendor(test_string, "username", "strognpassword", shop);
        return food;
    }
}