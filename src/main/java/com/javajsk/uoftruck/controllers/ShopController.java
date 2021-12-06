package com.javajsk.uoftruck.controllers;

import adapters.dam.entityrepoitories.ShopDB;
import entities.Addon;
import framework.MongoDB;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;

public class ShopController {


    ChangeShopStatus changeShopStatus;
    ModifyShop modifyShop;
    MongoDB db = new MongoDB();
    ShopDB shoprepository = new ShopDB(db);
    public ShopController(ChangeShopStatus changeShopStatus, ModifyShop modifyShop) {
        this.changeShopStatus = changeShopStatus;
        this.modifyShop = modifyShop;
    }
    @PutMapping("/changeshopstatus/{vendorToken}/{newStatus}")
    public ResponseObject runChangeShopStatus(@PathVariable String vendorToken,
                                    @PathVariable Boolean newStatus){
        return changeShopStatus.changeShopStatus(vendorToken, newStatus);
    }
    @PutMapping("/modifyshop/{vendorToken}")
    public ResponseObject runModifyShop(@RequestBody String shop, @PathVariable String vendorToken ){
        Shop shop1 = shoprepository.loadShopFromJSON(new JSONObject(shop));

        return modifyShop.modifyShop(vendorToken, shop1);
    }
}
