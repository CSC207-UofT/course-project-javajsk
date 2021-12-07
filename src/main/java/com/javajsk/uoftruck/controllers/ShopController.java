package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.ShopDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.dai.Repository;
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
import entities.Addon;
import entities.Vendor;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;
import presenters.ObjectPresenter;
import presenters.RepositoryPresenter;
import presenters.VendorPresenter;

import javax.swing.text.View;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ShopController {


    ChangeShopStatus changeShopStatus;
    ModifyShop modifyShop;
    ViewShop viewShop;
    ViewAllShops viewAllShops;
    MongoDB db;
    ShopDB shopRepository;
    VendorRepository vendorRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Shop> shopObjectBoundary;
    VendorBoundary vendorBoundary;


    public ShopController() {
        this.db = new MongoDB();
        this.shopRepository = new ShopDB(db);
        this.vendorRepository = new VendorDB(db);
        this.repositoryBoundary = new RepositoryPresenter();
        this.shopObjectBoundary = new ObjectPresenter<>();
        this.vendorBoundary = new VendorPresenter();
        this.viewAllShops = new ViewAllShopsInteractor();
        this.changeShopStatus = new ChangeShopStatusInteractor(vendorRepository, shopRepository, repositoryBoundary, shopObjectBoundary);
        this.modifyShop = new ModifyShopInteractor(vendorRepository, repositoryBoundary, shopRepository,vendorBoundary, shopObjectBoundary);
        this.viewShop = new ViewShopInteractor(shopRepository,shopObjectBoundary);

    }
    @PutMapping("/changeshopstatus/{vendorToken}/{newStatus}")
    public ResponseObject runChangeShopStatus(@PathVariable String vendorToken,
                                    @PathVariable Boolean newStatus){
        return changeShopStatus.changeShopStatus(vendorToken, newStatus);
    }
    @PutMapping("/modifyshop/{vendorToken}")
    public ResponseObject runModifyShop(@RequestBody String shop, @PathVariable String vendorToken ){
        Shop shop1 = shopRepository.loadShopFromJSON(new JSONObject(shop));

        return modifyShop.modifyShop(vendorToken, shop1);
    }
    @GetMapping("/GetShop/{shopId}")
    public ResponseObject viewShop(@PathVariable String shopId){
        return viewShop.viewShop(shopId);
    }

    @GetMapping("/ViewAllShops/")
    public ResponseObject viewAllShop(){
        return viewAllShops.viewAllShops();
    }
}
