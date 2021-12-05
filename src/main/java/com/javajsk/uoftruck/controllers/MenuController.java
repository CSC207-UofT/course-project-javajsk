package com.javajsk.uoftruck.controllers;

import businessrules.dai.VendorRepository;
import businessrules.menu.inputboundaries.*;
import businessrules.menu.usecases.AddAddonToMenuInteractor;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import entities.Food;
import entities.Menu;
import entities.Singleton;
import org.springframework.web.bind.annotation.*;

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
