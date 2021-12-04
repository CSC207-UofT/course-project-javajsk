package controllers;

import businessrules.menu.inputboundaries.*;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;
import entities.Food;
import entities.Menu;
import entities.Singleton;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    public MenuController(AddAddonToMenu addonToMenu, AddFoodToMenu addFoodToMenu,
                          GetAvailableAddons getAvailableAddons, GetAvailableFoods getAvailableFoods,
                          RemoveAddonFromMenu removeAddonFromMenu, RemoveFoodFromMenu removeFoodFromMenu,
                          SetAddonAvailability setAddonAvailability, SetSingletonAvailability setSingletonAvailability,
                          ViewMenu viewMenu) {
        this.addAddonToMenu = addonToMenu;
        this.addFoodToMenu = addFoodToMenu;
        this.getAvailableAddons = getAvailableAddons;
        this.getAvailableFoods = getAvailableFoods;
        this.removeAddonFromMenu = removeAddonFromMenu;
        this.removeFoodFromMenu = removeFoodFromMenu;
        this.setAddonAvailability = setAddonAvailability;
        this.setSingletonAvailability = setSingletonAvailability;
        this.viewMenu = viewMenu;
    }
    @PutMapping("/AddAddontoMenu/{vendorToken}")
    public Menu runAddAddontoMenu(@PathVariable String vendorToken, @RequestBody Addon addon){
        ResponseObject response = addAddonToMenu.addAddon(vendorToken, addon);
        return (Menu) response.getContents();
    }
    @PutMapping("/AddFoodtoMenu/{vendorToken}")
    public Menu runAddFoodtoMenu(@PathVariable String vendorToken, @RequestBody Food food){
        ResponseObject response = addFoodToMenu.addFood(vendorToken, food);
        return (Menu) response.getContents();
    }
    @GetMapping("/GetAvailableAddons/{shopId}")
    public List<Addon> runGetAvailableAddons(@PathVariable String shopId){
        ResponseObject response = getAvailableAddons.getAvailableAddons(shopId);
        //TODO: Check return type here
        return (List<Addon>) response.getContents();
    }

    @GetMapping("/GetAvailableAddons/{shopId}")
    public List<Food> runGetAvailableFoods(@PathVariable String shopId){
        ResponseObject response = getAvailableFoods.getAvailableFoods(shopId);
        //TODO: Check return type here
        return (List<Food>) response.getContents();
    }
    @PutMapping("/RemoveAddonfromMenu/{vendorToken}")
    public Menu runRemoveAddonFromMenu(@PathVariable String vendorToken, @RequestBody Addon addon){
        ResponseObject response = removeAddonFromMenu.removeAddon(vendorToken, addon);
        return (Menu) response.getContents();
    }
    @PutMapping("/RemoveFoodfromMenu/{vendorToken}")
    public Menu runRemoveFoodFromMenu(@PathVariable String vendorToken, @RequestBody Food food){
        ResponseObject response = removeFoodFromMenu.removeFood(vendorToken, food);
        return (Menu) response.getContents();
    }
    @PutMapping("/SetAddonAvailability/{vendorToken}/{availability}")
    public Menu runSetAddonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody Addon addon){
        ResponseObject response = setAddonAvailability.setAddonAvailability(vendorToken, addon, availability);
        return (Menu) response.getContents();

    }
    @PutMapping("/SetAddonAvailability/{vendorToken}/{availability}")
    public Menu runSetSingletonAvailability(@PathVariable String vendorToken, @PathVariable Boolean availability,
                                        @RequestBody Singleton singleton){
        ResponseObject response = setSingletonAvailability.setSingletonAvailability(vendorToken, singleton, availability);
        return (Menu) response.getContents();

    }
    @GetMapping("/ViewMenu/{shopId}")
    public Menu runViewMenu(@PathVariable String shopId){
       ResponseObject response =  viewMenu.viewMenu(shopId);
        return (Menu) response.getContents();
    }


}
