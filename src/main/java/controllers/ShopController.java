package controllers;

import org.springframework.web.bind.annotation.*;
import adapters.dam.entityrepoitories.ShopDB;
import adapters.dam.entityrepoitories.VendorDB;
import businessrules.outputboundaries.ResponseObject;
import businessrules.shop.inputboundaries.ChangeShopStatus;
import businessrules.shop.inputboundaries.ModifyShop;
import entities.Shop;
import org.json.JSONObject;

@RestController
public class ShopController {


    ChangeShopStatus changeShopStatus;
    ModifyShop modifyShop;

    public ShopController(ChangeShopStatus changeShopStatus, ModifyShop modifyShop) {
        this.changeShopStatus = changeShopStatus;
        this.modifyShop = modifyShop;
    }
    @PutMapping("/changeshopstatus/{vendorToken}/{newStatus}")
    public Shop runChangeShopStatus(@PathVariable String vendorToken,
                                    @PathVariable Boolean newStatus){
        ResponseObject response = changeShopStatus.changeShopStatus(vendorToken, newStatus);
        return (Shop) response.getContents();
    }
    @PutMapping("/modifyshop/{vendorToken}")
    public Shop runModifyShop(@RequestBody Shop shop, @PathVariable String vendorToken ){
        ResponseObject response = modifyShop.modifyShop(vendorToken, shop);
        return (Shop) response.getContents();
    }
}
