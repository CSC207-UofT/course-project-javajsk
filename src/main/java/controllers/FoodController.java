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
