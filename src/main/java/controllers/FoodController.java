package controllers;

import adapters.dam.entityrepoitories.FoodDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.food.inputboundaries.AddSingleton;
import businessrules.food.inputboundaries.CreateFood;
import businessrules.food.inputboundaries.GetShopFoods;
import businessrules.food.inputboundaries.ModifyFood;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import entities.Food;
import entities.Singleton;
import entities.Vendor;
import org.springframework.web.bind.annotation.*;

public class FoodController {

    AddSingleton addSingleton;
    CreateFood createFood;
    GetShopFoods getShopFoods;
    ModifyFood modifyFood;

    public FoodController(AddSingleton addSingleton, CreateFood createFood, GetShopFoods getShopFoods, ModifyFood modifyFood) {
        this.addSingleton = addSingleton;
        this.createFood = createFood;
        this.getShopFoods = getShopFoods;
        this.modifyFood = modifyFood;
    }
    @PutMapping("/AddSingleton/{vendorToken}/{foodId}")
    public Food runAddSingleton(@PathVariable String vendorToken, @PathVariable String foodId,
                                @RequestBody Singleton singleton){

        ResponseObject response = addSingleton.addSingleton(vendorToken, foodId, singleton);
        return (Food) response.getContents();
    }
    @PostMapping("/CreateFood/{vendorToken}")
    public Food runCreateFood(@PathVariable String vendorToken, @RequestBody Food food){
        ResponseObject response = createFood.createFood(vendorToken,food);
        return (Food) response.getContents();
    }
    @GetMapping("/GetShopFoods/{shopId}")
    public Food runGetShopFoods(@PathVariable String shopId){
        ResponseObject responseObject = getShopFoods.getShopFoods(shopId);
        return (Food) responseObject.getContents();
    }
    @PutMapping("/ModifyFood/{vendorToken}/{foodId}")
    public Food runModifyFood(@PathVariable String vendorToken, @PathVariable String foodId,
                              @RequestBody Food food){
        ResponseObject response = modifyFood.modifyFood(vendorToken, foodId, food);
        return (Food) response.getContents();
    }

}
