package com.javajsk.uoftruck.controllers;

import org.springframework.web.bind.annotation.*;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;

public class ShopController {


    ChangeShopStatus changeShopStatus;
    ModifyShop modifyShop;

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
    public ResponseObject runModifyShop(@RequestBody Shop shop, @PathVariable String vendorToken ){
        return modifyShop.modifyShop(vendorToken, shop);
    }
}
