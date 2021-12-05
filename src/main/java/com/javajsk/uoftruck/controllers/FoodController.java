package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.CustomerDB;
import adapters.dam.entityrepoitories.FoodDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
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
import org.springframework.web.bind.annotation.*;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

@RestController
public class FoodController {

    AddSingleton addSingleton;
    CreateFood createFood;
    GetShopFoods getShopFoods;
    ModifyFood modifyFood;
    MongoDB db = new MongoDB();
    VendorRepository vendorRepository = new VendorDB(db);
    Repository<Food> foodRepository = new FoodDB(db);
    CustomerRepository customerRepository = new CustomerDB(db);
    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Food> foodObjectBoundary = new ObjectPresenter<Food>();

    public FoodController() {
        this.addSingleton = new AddSingletonInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary,  vendorBoundary);
        this.createFood = new CreateFoodInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary);
        this.getShopFoods = new GetShopFoodsInteractor(foodRepository, foodObjectBoundary);
        this.modifyFood = new ModifyFoodInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary, vendorBoundary);
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
