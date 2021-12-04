package main.java.controllers;

import adapters.dam.entityrepoitories.ShopDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;
import org.json.JSONObject;

public class ShopController {

    ShopDB shopDB;
    VendorDB vendorDB;
    ChangeShopStatus changeShopStatus;
    ModifyShop modifyShop;


    public Shop runChangeShopStatus(String input){
        JSONObject data = new JSONObject(input);
        String vendorToken = data.getString("vendorToken");
        Boolean newStatus = data.getBoolean("newStatus");
        ResponseObject response = changeShopStatus.changeShopStatus(vendorToken, newStatus);
    }
}
