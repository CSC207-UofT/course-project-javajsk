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

/**
 * Controller for food use case
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    /**
     * The Add singleton input boundary.
     */
    AddSingleton addSingleton;
    /**
     * The Create food input boundary.
     */
    CreateFood createFood;
    /**
     * The Get shop foods input boundary.
     */
    GetShopFoods getShopFoods;
    /**
     * The Modify food input boundary.
     */
    ModifyFood modifyFood;
    /**
     * The database.
     */
    MongoDB db = new MongoDB();
    /**
     * The Vendor repository.
     */
    VendorDB vendorRepository = new VendorDB(db);
    /**
     * The Food repository.
     */
    FoodDB foodRepository = new FoodDB(db);
    /**
     * The Singleton repository.
     */
    SingletonDB singletonRepository = new SingletonDB(db);
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository = new CustomerDB(db);
    /**
     * The Vendor output boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Food object output boundary.
     */
    ObjectBoundary<Food> foodObjectBoundary = new ObjectPresenter<>();

    /**
     * Instantiates a new Food controller.
     */
    public FoodController() {
        this.addSingleton = new AddSingletonInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary, vendorBoundary);
        this.createFood = new CreateFoodInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary);
        this.getShopFoods = new GetShopFoodsInteractor(foodRepository, foodObjectBoundary);
        this.modifyFood = new ModifyFoodInteractor(vendorRepository, foodRepository, repositoryBoundary, foodObjectBoundary, vendorBoundary);
    }

    /**
     * Runs add singleton use case.
     *
     * @param vendorToken The token of the vendor who wants to add to their menu
     * @param foodId      The id of the food the singleton gets added ot
     * @param singleton   the singleton that needs to get added
     * @return A ResponseObject with the singleton object just added, with status codes
     */
    @PutMapping("/AddSingleton/{vendorToken}/{foodId}")
    public ResponseObject runAddSingleton(@PathVariable String vendorToken, @PathVariable String foodId,
                                          @RequestBody String singleton) {
        Singleton singleton1 = singletonRepository.loadSingletonFromJSON(new JSONObject(singleton));
        return addSingleton.addSingleton(vendorToken, foodId, singleton1);
    }

    /**
     * Runs create food use case.
     *
     * @param vendorToken The token of the vendor who wants to make a food object
     * @param food        the food that needs to get created
     * @return A ResponseObject with the food object just created, with status codes
     */
    @PostMapping("/CreateFood/{vendorToken}")
    public ResponseObject runCreateFood(@PathVariable String vendorToken, @RequestBody String food) {
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return createFood.createFood(vendorToken, food1);
    }

    /**
     * Runs get shop foods use case.
     *
     * @param shopId the id of the shop we want to get foods from
     * @return A ResponseObject with the food objects from the given shops, with status codes
     */
    @GetMapping("/GetShopFoods/{shopId}")
    public ResponseObject runGetShopFoods(@PathVariable String shopId) {
        return getShopFoods.getShopFoods(shopId);
    }

    /**
     * Runs modify food use case.
     *
     * @param vendorToken The token of the vendor who wants to modify a food object
     * @param foodId      The id of the food trying to be modified
     * @param food        The new food that will replace the old food
     * @return A ResponseObject with the new updated food object, with status codes
     */
    @PutMapping("/ModifyFood/{vendorToken}/{foodId}")
    public ResponseObject runModifyFood(@PathVariable String vendorToken, @PathVariable String foodId,
                                        @RequestBody String food) {
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return modifyFood.modifyFood(vendorToken, foodId, food1);
    }
}
