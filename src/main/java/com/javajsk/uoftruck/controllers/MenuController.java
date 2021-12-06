package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.*;
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
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

@RestController
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

    MongoDB db = new MongoDB();
    Repository<Shop> shopRepository = new ShopDB(db);
    SingletonDB singletonRepository = new SingletonDB(db);
    VendorRepository vendorRepository = new VendorDB(db);
    AddonDB addonRepository = new AddonDB(db);
    FoodDB foodRepository = new FoodDB(db);

    VendorBoundary vendorBoundary = new VendorPresenter();
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    ObjectBoundary<Menu> menuObjectBoundary = new ObjectPresenter<Menu>();
    ObjectBoundary<Addon> addonObjectBoundary = new ObjectPresenter<>();
    ObjectBoundary<Food> foodObjectBoundary = new ObjectPresenter<>();

    public MenuController(){
        this.addAddonToMenu = new AddAddonToMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        this.addFoodToMenu = new AddFoodToMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        this.getAvailableAddons = new GetAvailableAddonsInteractor(shopRepository, repositoryBoundary, addonObjectBoundary);
        this.getAvailableFoods = new GetAvailableFoodsInteractor(shopRepository, repositoryBoundary, foodObjectBoundary);
        this.removeAddonFromMenu = new RemoveAddonFromMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        this.removeFoodFromMenu = new RemoveFoodFromMenuInteractor(vendorRepository, repositoryBoundary, vendorBoundary, shopRepository, menuObjectBoundary);
        this.setAddonAvailability = new SetAddonAvailabilityInteractor(menuObjectBoundary, vendorRepository, repositoryBoundary, vendorBoundary, shopRepository);
        this.setSingletonAvailability = new SetSingletonAvailabilityInteractor(menuObjectBoundary, vendorRepository, repositoryBoundary, vendorBoundary,singletonRepository, shopRepository);
        this.viewMenu =new ViewMenuInteractor(shopRepository, repositoryBoundary, menuObjectBoundary);
    }

    @PutMapping("/AddAddontoMenu/{vendorToken}")
    public ResponseObject runAddAddontoMenu(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        ResponseObject response = addAddonToMenu.addAddon(vendorToken, addon1);
        return response;
    }
    @PutMapping("/AddFoodtoMenu/{vendorToken}")
    public ResponseObject runAddFoodtoMenu(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        ResponseObject response = addFoodToMenu.addFood(vendorToken, food1);
        return response;
    }
    @GetMapping("/GetAvailableAddons/{shopId}")
    public ResponseObject runGetAvailableAddons(@PathVariable String shopId){
        ResponseObject response = getAvailableAddons.getAvailableAddons(shopId);
        return response;
    }

    @GetMapping("/GetAvailableAddons1/{shopId}")
    public ResponseObject runGetAvailableFoods(@PathVariable String shopId){
        ResponseObject response = getAvailableFoods.getAvailableFoods(shopId);
        return response;
    }
    @PutMapping("/RemoveAddonfromMenu/{vendorToken}")
    public ResponseObject runRemoveAddonFromMenu(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        ResponseObject response = removeAddonFromMenu.removeAddon(vendorToken, addon1);
        return response;
    }
    @PutMapping("/RemoveFoodfromMenu/{vendorToken}")
    public ResponseObject runRemoveFoodFromMenu(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        ResponseObject response = removeFoodFromMenu.removeFood(vendorToken, food1);
        return response;
    }
    @PutMapping("/SetAddonAvailability1/{vendorToken}/{availability}")
    public ResponseObject runSetAddonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return setAddonAvailability.setAddonAvailability(vendorToken, addon1, availability);

    }
    @PutMapping("/SetAddonAvailability/{vendorToken}/{availability}")
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
