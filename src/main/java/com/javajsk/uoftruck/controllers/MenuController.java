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

/**
 * The type Menu controller.
 */
/**/
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController{

    /**
     * The Add addon to menu.
     */
    AddAddonToMenu addAddonToMenu;
    /**
     * The Add food to menu.
     */
    AddFoodToMenu addFoodToMenu;
    /**
     * The Get available addons.
     */
    GetAvailableAddons getAvailableAddons;
    /**
     * The Get available foods.
     */
    GetAvailableFoods getAvailableFoods;
    /**
     * The Remove addon from menu.
     */
    RemoveAddonFromMenu removeAddonFromMenu;
    /**
     * The Remove food from menu.
     */
    RemoveFoodFromMenu removeFoodFromMenu;
    /**
     * The Set addon availability.
     */
    SetAddonAvailability setAddonAvailability;
    /**
     * The Set singleton availability.
     */
    SetSingletonAvailability setSingletonAvailability;
    /**
     * The View menu.
     */
    ViewMenu viewMenu;
    /**
     * The Db.
     */
    MongoDB db;
    /**
     * The Shop repository.
     */
    Repository<Shop> shopRepository;
    /**
     * The Singleton repository.
     */
    SingletonDB singletonRepository;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Addon repository.
     */
    AddonDB addonRepository;
    /**
     * The Food repository.
     */
    FoodDB foodRepository;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary = new VendorPresenter();
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary = new RepositoryPresenter();
    /**
     * The Menu object boundary.
     */
    ObjectBoundary<Menu> menuObjectBoundary = new ObjectPresenter<>();
    /**
     * The Addon object boundary.
     */
    ObjectBoundary<Addon> addonObjectBoundary = new ObjectPresenter<>();
    /**
     * The Food object boundary.
     */
    ObjectBoundary<Food> foodObjectBoundary = new ObjectPresenter<>();

    /**
     * Instantiates a new Menu controller.
     */
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

    /**
     * Run add addonto menu response object.
     *
     * @param vendorToken Token associated with vendor trying to add to their menu
     * @param addon       The addon object they want to add ot their menu
     * @return A ResponseObject with the menu with the newly added addon, with status codes
     */
    @PutMapping("/AddAddonToMenu/{vendorToken}")
    public ResponseObject runAddAddontoMenu(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return addAddonToMenu.addAddon(vendorToken, addon1);
    }

    /**
     * Run add foodto menu response object.
     *
     * @param vendorToken Token associated with vendor trying to add to their menu
     * @param food        The food object they want to add ot their menu
     * @return A ResponseObject with the menu with the newly added food, with status codes
     */
    @PutMapping("/AddFoodToMenu/{vendorToken}")
    public ResponseObject runAddFoodtoMenu(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return addFoodToMenu.addFood(vendorToken, food1);
    }

    /**
     * Run get available addons response object.
     *
     * @param shopId The shopid of the shop we want to  check available addons of
     * @return A ResponseObject with all the available addons of the given shop, with status codes
     */
    @GetMapping("/GetAvailableAddons/{shopId}")
    public ResponseObject runGetAvailableAddons(@PathVariable String shopId){
        return getAvailableAddons.getAvailableAddons(shopId);
    }

    /**
     * Run get available foods response object.
     *
     * @param shopId The shopid of the shop we want to  check available foods of
     * @return A ResponseObject with all the available foods of the given shop, with status codes
     */
    @GetMapping("/GetAvailableFoods/{shopId}")
    public ResponseObject runGetAvailableFoods(@PathVariable String shopId){
        return getAvailableFoods.getAvailableFoods(shopId);
    }

    /**
     * Run remove addon from menu response object.
     *
     * @param vendorToken Token associated with vendor trying to remove from their menu
     * @param addon       The addon object they want to remove from their menu
     * @return A ResponseObject with the menu with the recently removed addon, with status codes
     */
    @PutMapping("/RemoveAddonFromMenu/{vendorToken}")
    public ResponseObject runRemoveAddonFromMenu(@PathVariable String vendorToken, @RequestBody String addon){
        Addon addon1 = addonRepository.loadAddonFromJSON(new JSONObject(addon));
        return removeAddonFromMenu.removeAddon(vendorToken, addon1);
    }

    /**
     * Run remove food from menu response object.
     *
     * @param vendorToken Token associated with vendor trying to remove from their menu
     * @param food        The food object they want to remove from their menu
     * @return A ResponseObject with the menu with the newly removed food, with status codes
     */
    @PutMapping("/RemoveFoodFromMenu/{vendorToken}")
    public ResponseObject runRemoveFoodFromMenu(@PathVariable String vendorToken, @RequestBody String food){
        Food food1 = foodRepository.loadFoodFromJSON(new JSONObject(food));
        return removeFoodFromMenu.removeFood(vendorToken, food1);
    }

    /**
     * Run view menu response object.
     *
     * @param shopId the shop id of the menu we're trying to view
     * @return A ResponseObject with the menu of the given shop, with status codes
     */
    @GetMapping("/ViewMenu/{shopId}")
    public ResponseObject runViewMenu(@PathVariable String shopId){
        return viewMenu.viewMenu(shopId);
    }
}
