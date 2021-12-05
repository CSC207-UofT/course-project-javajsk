package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.*;
import businessrules.dai.CustomerRepository;
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
import org.springframework.web.bind.annotation.*;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

import java.util.List;
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
    Repository<Singleton> singletonRepository = new SingletonDB(db);
    CustomerRepository customerRepository = new CustomerDB(db);
    VendorRepository vendorRepository = new VendorDB(db);
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
    public ResponseObject runAddAddontoMenu(@PathVariable String vendorToken, @RequestBody Addon addon){
        ResponseObject response = addAddonToMenu.addAddon(vendorToken, addon);
        return response;
    }
    @PutMapping("/AddFoodtoMenu/{vendorToken}")
    public ResponseObject runAddFoodtoMenu(@PathVariable String vendorToken, @RequestBody Food food){
        ResponseObject response = addFoodToMenu.addFood(vendorToken, food);
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
    public ResponseObject runRemoveAddonFromMenu(@PathVariable String vendorToken, @RequestBody Addon addon){
        ResponseObject response = removeAddonFromMenu.removeAddon(vendorToken, addon);
        return response;
    }
    @PutMapping("/RemoveFoodfromMenu/{vendorToken}")
    public ResponseObject runRemoveFoodFromMenu(@PathVariable String vendorToken, @RequestBody Food food){
        ResponseObject response = removeFoodFromMenu.removeFood(vendorToken, food);
        return response;
    }
    @PutMapping("/SetAddonAvailability1/{vendorToken}/{availability}")
    public ResponseObject runSetAddonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody Addon addon){
        return setAddonAvailability.setAddonAvailability(vendorToken, addon, availability);

    }
    @PutMapping("/SetAddonAvailability/{vendorToken}/{availability}")
    public ResponseObject runSetSingletonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody Singleton singleton){
        return setSingletonAvailability.setSingletonAvailability(vendorToken, singleton, availability);

    }
    @GetMapping("/ViewMenu/{shopId}")
    public ResponseObject runViewMenu(@PathVariable String shopId){
        return viewMenu.viewMenu(shopId);
    }


}
