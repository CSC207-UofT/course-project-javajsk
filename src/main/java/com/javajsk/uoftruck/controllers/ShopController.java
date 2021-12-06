package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.ShopDB;
import businessrules.shop.inputboundaries.ViewShop;
import businessrules.shop.usecases.ChangeShopStatusInteractor;
import businessrules.shop.usecases.ModifyShopInteractor;
import businessrules.shop.usecases.ViewShopInteractor;
import entities.Addon;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ShopController {


    ChangeShopStatus changeShopStatus;
    ModifyShop modifyShop;
    ViewShop viewShop;
    MongoDB db = new MongoDB();
    ShopDB shopRepository = new ShopDB(db);

    public ShopController() {
//        this.changeShopStatus = new ChangeShopStatusInteractor();
//        this.modifyShop = new ModifyShopInteractor();
        //this.viewShop = new ViewShopInteractor(shopRepository,);

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
}
