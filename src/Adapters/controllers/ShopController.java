package Adapters.controllers;

import businessrules.shop.inputboundaries.CreateShopInputBoundary;
import businessrules.shop.inputboundaries.DeleteShopInputBoundary;
import businessrules.shop.inputboundaries.ReadShopInputBoundary;
import businessrules.shop.inputboundaries.UpdateShopInputBoundary;
import org.json.JSONObject;

public class ShopController {

    CreateShopInputBoundary createShopInputBoundary;
    DeleteShopInputBoundary deleteShopInputBoundary;
    UpdateShopInputBoundary updateShopInputBoundary;
    ReadShopInputBoundary readShopInputBoundary;

    public ShopController(CreateShopInputBoundary createShopInputBoundary,
                          DeleteShopInputBoundary deleteShopInputBoundary,
                          UpdateShopInputBoundary updateShopInputBoundary,
                          ReadShopInputBoundary readShopInputBoundary){
        this.createShopInputBoundary = createShopInputBoundary;
        this.deleteShopInputBoundary = deleteShopInputBoundary;
        this.readShopInputBoundary = readShopInputBoundary;
        this.updateShopInputBoundary = updateShopInputBoundary;
    }

    public JSONObject runUpdateShop(String input){

        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("shopId") &&
                update_data.has("shopObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String shopId = update_data.getString("shopID");
        JSONObject shopObject = update_data.getJSONObject("shopObject");


        return this.updateShopInputBoundary.updateShop(vendorToken, shopId,shopObject);
    }

    public JSONObject runCreateShop(String input){
        JSONObject create_data = new JSONObject(input);
        if(!(create_data.has("shopID") && create_data.has("shopObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_shop = create_data.getJSONObject("shopObject");


        return this.createShopInputBoundary.createShop(vendorToken,new_shop);
    }


    public JSONObject runDeleteShop(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!(delete_data.has("shopID") && delete_data.has("vendorToken"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String shopId = delete_data.getString("shopId");


        return this.deleteShopInputBoundary.deleteShop(vendorToken,shopId);
    }

    public JSONObject runReadShop(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("shopId")){

            //TODO:Call presenter with error message
        }
        String shopId = read_data.getString("shopId");


        return this.readShopInputBoundary.readShop(shopId);
    }
}
