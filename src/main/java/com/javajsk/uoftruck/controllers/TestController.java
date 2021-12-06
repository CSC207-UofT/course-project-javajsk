package com.javajsk.uoftruck.controllers;

import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @PostMapping("/test/{idk}")
    public ResponseObject test(@PathVariable String idk, @RequestBody String help){
        List<Integer> types = new ArrayList<>();
        types.add(1);
        types.add(2);
        Addon addon = new Addon("61ad42479c148d67937d63f9", "ketchup", (float)0.50, types,
                true, "n/a");
        addon.setName(help);
        return new ResponseObject(200, "success", addon);
    }
}
