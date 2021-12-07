package com.javajsk.uoftruck.controllers;

import adapters.dam.*;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.*;
import businessrules.menu.usecases.*;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController{

    AddAddonToMenu addAddonToMenu;
    AddFoodToMenu addFoodToMenu;
    GetAvailableAddons getAvailableAddons;
    GetAvailableFoods getAvailableFoods;
    RemoveAddonFromMenu removeAddonFromMenu;
    RemoveFoodFromMenu removeFoodFromMenu;
    SetAddonAvailability setAddonAvailability;
    SetSingletonAvailability setSingletonAvailability;
    ViewMenu viewMenu;
    MongoDB db;
    Repository<Shop> shopRepository;
    SingletonDB singletonRepository;
    VendorRepository vendorRepository;
    AddonDB addonRepository;
    FoodDB foodRepository;
    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Menu> menuObjectBoundary = new ObjectPresenter<>();
    ObjectBoundary<Addon> addonObjectBoundary = new ObjectPresenter<>();
    ObjectBoundary<Food> foodObjectBoundary = new ObjectPresenter<>();

    public MenuController(){
        this.db = new MongoDB();
        this.shopRepository = new ShopDB(db);
        this.singletonRepository = new SingletonDB(db);
        this.vendorRepository = new VendorDB(db);
        this.addonRepository = new AddonDB(db);
        this.foodRepository = new FoodDB(db);

        this.addAddonToMenu = new AddAddonToMenuInteractor(vendorRepository, repositoryBoundary,
                vendorBoundary, shopRepository, menuObjectBoundary);
        this.addFoodToMenu = new AddFoodToMenuInteractor(vendorRepository, repositoryBoundary,
                vendorBoundary, shopRepository, menuObjectBoundary);
        this.getAvailableAddons = new GetAvailableAddonsInteractor(shopRepository, repositoryBoundary,
                addonObjectBoundary);
        this.getAvailableFoods = new GetAvailableFoodsInteractor(shopRepository, repositoryBoundary,
                foodObjectBoundary);
        this.removeAddonFromMenu = new RemoveAddonFromMenuInteractor(vendorRepository, repositoryBoundary,
                vendorBoundary, shopRepository, menuObjectBoundary);
        this.removeFoodFromMenu = new RemoveFoodFromMenuInteractor(vendorRepository, repositoryBoundary,
                vendorBoundary, shopRepository, menuObjectBoundary);
        this.setAddonAvailability = new SetAddonAvailabilityInteractor(menuObjectBoundary, vendorRepository,
                repositoryBoundary, vendorBoundary, shopRepository);
        this.setSingletonAvailability = new SetSingletonAvailabilityInteractor(menuObjectBoundary, vendorRepository,
                repositoryBoundary, vendorBoundary,singletonRepository, shopRepository);
        this.viewMenu =new ViewMenuInteractor(shopRepository, repositoryBoundary, menuObjectBoundary);
    }

    @PutMapping("/AddAddonToMenu/{vendorToken}")
    public ResponseObject runAddAddontoMenu(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return addAddonToMenu.addAddon(vendorToken, addon1);
    }
    @PutMapping("/AddFoodToMenu/{vendorToken}")
    public ResponseObject runAddFoodtoMenu(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return addFoodToMenu.addFood(vendorToken, food1);
    }
    @GetMapping("/GetAvailableAddons/{shopId}")
    public ResponseObject runGetAvailableAddons(@PathVariable String shopId){
        return getAvailableAddons.getAvailableAddons(shopId);
    }

    @GetMapping("/GetAvailableFoods/{shopId}")
    public ResponseObject runGetAvailableFoods(@PathVariable String shopId){
        return getAvailableFoods.getAvailableFoods(shopId);
    }

    @PutMapping("/RemoveAddonFromMenu/{vendorToken}")
    public ResponseObject runRemoveAddonFromMenu(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return removeAddonFromMenu.removeAddon(vendorToken, addon1);
    }

    @PutMapping("/RemoveFoodFromMenu/{vendorToken}")
    public ResponseObject runRemoveFoodFromMenu(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return removeFoodFromMenu.removeFood(vendorToken, food1);
    }

    @PutMapping("/SetAddonAvailability/{vendorToken}/{availability}")
    public ResponseObject runSetAddonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return setAddonAvailability.setAddonAvailability(vendorToken, addon1, availability);

    }
    @PutMapping("/SetSingletonAvailability/{vendorToken}/{availability}")
    public ResponseObject runSetSingletonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody String singleton){
        Singleton singleton1 = singletonRepository.loadSingletonFromJSON(new JSONObject(singleton));
        return setSingletonAvailability.setSingletonAvailability(vendorToken, singleton1, availability);

    }
    @GetMapping("/ViewMenu/{shopId}")
    public ResponseObject runViewMenu(@PathVariable String shopId){
        return viewMenu.viewMenu(shopId);
    }
}
