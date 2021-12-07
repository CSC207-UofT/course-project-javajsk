package com.javajsk.uoftruck.controllers;

import adapters.dam.CustomerDB;
import adapters.dam.FoodDB;
import adapters.dam.SingletonDB;
import adapters.dam.VendorDB;
import businessrules.dai.CustomerRepository;
import businessrules.food.inputboundaries.AddSingleton;
import businessrules.food.inputboundaries.CreateFood;
import businessrules.food.inputboundaries.GetShopFoods;
import businessrules.food.inputboundaries.ModifyFood;
import businessrules.food.usecases.AddSingletonInteractor;
import businessrules.food.usecases.CreateFoodInteractor;
import businessrules.food.usecases.GetShopFoodsInteractor;
import businessrules.food.usecases.ModifyFoodInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Food;
import entities.Singleton;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    AddSingleton addSingleton;
    CreateFood createFood;
    GetShopFoods getShopFoods;
    ModifyFood modifyFood;
    MongoDB db = new MongoDB();
    VendorDB vendorRepository = new VendorDB(db);
    FoodDB foodRepository = new FoodDB(db);
    SingletonDB singletonRepository = new SingletonDB(db);
    CustomerRepository customerRepository = new CustomerDB(db);
    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Food> foodObjectBoundary = new ObjectPresenter<>();

    public FoodController() {
        this.addSingleton = new AddSingletonInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary,  vendorBoundary);
        this.createFood = new CreateFoodInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary);
        this.getShopFoods = new GetShopFoodsInteractor(foodRepository, foodObjectBoundary);
        this.modifyFood = new ModifyFoodInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary, vendorBoundary);
    }

    @PutMapping("/AddSingleton/{vendorToken}/{foodId}")
    public ResponseObject runAddSingleton(@PathVariable String vendorToken, @PathVariable String foodId,
                                @RequestBody String singleton){
        Singleton singleton1 = singletonRepository.loadSingletonFromJSON(new JSONObject(singleton));
        return addSingleton.addSingleton(vendorToken, foodId, singleton1);
    }
    @PostMapping("/CreateFood/{vendorToken}")
    public ResponseObject runCreateFood(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return createFood.createFood(vendorToken,food1);
    }
    @GetMapping("/GetShopFoods/{shopId}")
    public ResponseObject runGetShopFoods(@PathVariable String shopId){
        return getShopFoods.getShopFoods(shopId);
    }

    @PutMapping("/ModifyFood/{vendorToken}/{foodId}")
    public ResponseObject runModifyFood(@PathVariable String vendorToken, @PathVariable String foodId,
                              @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return modifyFood.modifyFood(vendorToken, foodId, food1);
    }
}
