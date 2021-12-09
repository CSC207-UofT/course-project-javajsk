package com.javajsk.uoftruck.controllers;

import adapters.dam.ShopDB;
import adapters.dam.VendorDB;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import businessrules.shop.inputboundaries.ViewAllShops;
import businessrules.shop.inputboundaries.ViewShop;
import businessrules.shop.usecases.ChangeShopStatusInteractor;
import businessrules.shop.usecases.ModifyShopInteractor;
import businessrules.shop.usecases.ViewAllShopsInteractor;
import businessrules.shop.usecases.ViewShopInteractor;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;
import adapters.presenters.ObjectPresenter;
import adapters.presenters.RepositoryPresenter;
import adapters.presenters.VendorPresenter;

import javax.swing.text.View;

/**
 * Controller for shop use cases
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ShopController {

    /**
     * The Change shop status input boundary.
     */
    ChangeShopStatus changeShopStatus;
    /**
     * The Modify shop input boundary.
     */
    ModifyShop modifyShop;
    /**
     * The View shop input boundary.
     */
    ViewShop viewShop;
    /**
     * The View all shops input boundary.
     */
    ViewAllShops viewAllShops;
    /**
     * The database.
     */
    MongoDB db;
    /**
     * The Shop repository.
     */
    ShopDB shopRepository;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Repository output boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Shop object output boundary.
     */
    ObjectBoundary<Shop> shopObjectBoundary;
    /**
     * The Vendor output boundary.
     */
    VendorBoundary vendorBoundary;


    /**
     * Instantiates a new Shop controller.
     */
    public ShopController() {
        this.db = new MongoDB();
        this.shopRepository = new ShopDB(db);
        this.vendorRepository = new VendorDB(db);
        this.repositoryBoundary = new RepositoryPresenter();
        this.shopObjectBoundary = new ObjectPresenter<>();
        this.vendorBoundary = new VendorPresenter();
        this.viewAllShops = new ViewAllShopsInteractor();
        this.changeShopStatus = new ChangeShopStatusInteractor(vendorRepository, shopRepository,
                repositoryBoundary, shopObjectBoundary);
        this.modifyShop = new ModifyShopInteractor(vendorRepository, repositoryBoundary, shopRepository, vendorBoundary, shopObjectBoundary);
        this.viewShop = new ViewShopInteractor(shopRepository, shopObjectBoundary);

    }

    /**
     * Runs change shop status use case.
     *
     * @param vendorToken the vendor token
     * @param newStatus   the new status
     * @return response object containing data to display to user
     */
    @PutMapping("/ChangeShopStatus/{vendorToken}/{newStatus}")
    public ResponseObject runChangeShopStatus(@PathVariable String vendorToken,
                                              @PathVariable Boolean newStatus) {
        return changeShopStatus.changeShopStatus(vendorToken, newStatus);
    }

    /**
     * Runs modify shop use case.
     *
     * @param shop        the shop
     * @param vendorToken the vendor token
     * @return response object containing data to display to user
     */
    @PutMapping("/ModifyShop/{vendorToken}")
    public ResponseObject runModifyShop(@RequestBody String shop, @PathVariable String vendorToken) {
        Shop shop1 = shopRepository.loadShopFromJSON(new JSONObject(shop));

        return modifyShop.modifyShop(vendorToken, shop1);
    }

    /**
     * Runs view shop use case.
     *
     * @param shopId the shop id
     * @return response object containing data to display to user
     */
    @GetMapping("/GetShop/{shopId}")
    public ResponseObject viewShop(@PathVariable String shopId) {
        return viewShop.viewShop(shopId);
    }

    /**
     * Runs view all shops use case.
     *
     * @return response object containing data to display to user
     */
    @GetMapping("/ViewAllShops/")
    public ResponseObject viewAllShop() {
        return viewAllShops.viewAllShops();
    }
}
