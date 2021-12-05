package com.javajsk.uoftruck.controllers;

import businessrules.food.inputboundaries.AddSingleton;
import businessrules.food.inputboundaries.CreateFood;
import businessrules.food.inputboundaries.GetShopFoods;
import businessrules.food.inputboundaries.ModifyFood;
import businessrules.outputboundaries.ResponseObject;
import entities.Food;
import entities.Singleton;
import org.springframework.web.bind.annotation.*;
@RestController
public class FoodController {

    AddSingleton addSingleton;
    CreateFood createFood;
    GetShopFoods getShopFoods;
    ModifyFood modifyFood;


    @PutMapping("/AddSingleton/{vendorToken}/{foodId}")
    public Food runAddSingleton(@PathVariable String vendorToken, @PathVariable String foodId,
                                @RequestBody Singleton singleton){

        ResponseObject response = addSingleton.addSingleton(vendorToken, foodId, singleton);
        return (Food) response.getContents();
    }
    @PostMapping("/CreateFood/{vendorToken}")
    public ResponseObject runCreateFood(@PathVariable String vendorToken, @RequestBody Food food){
        return createFood.createFood(vendorToken,food);
    }
    @GetMapping("/GetShopFoods/{shopId}")
    public ResponseObject runGetShopFoods(@PathVariable String shopId){
        return getShopFoods.getShopFoods(shopId);
    }
    @PutMapping("/ModifyFood/{vendorToken}/{foodId}")
    public ResponseObject runModifyFood(@PathVariable String vendorToken, @PathVariable String foodId,
                              @RequestBody Food food){
        return modifyFood.modifyFood(vendorToken, foodId, food);
    }


}
