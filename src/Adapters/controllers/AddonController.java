package Adapters.controllers;

import UseCases.Orders.CreateOrderInputBoundary;
import businessrules.addon.inputboundaries.CreateAddonInputBoundary;
import businessrules.addon.inputboundaries.DeleteAddonInputBoundary;
import businessrules.addon.inputboundaries.ReadAddonInputBoundary;
import businessrules.addon.inputboundaries.UpdateAddonInputBoundary;
import org.json.JSONObject;

public class AddonController {
    CreateAddonInputBoundary createAddonInputBoundary;
    DeleteAddonInputBoundary deleteAddonInputBoundary;
    ReadAddonInputBoundary readAddonInputBoundary;
    UpdateAddonInputBoundary updateAddonInputBoundary;

    public AddonController(CreateAddonInputBoundary createAddonInputBoundary,DeleteAddonInputBoundary deleteAddonInputBoundary,
                           ReadAddonInputBoundary readAddonInputBoundary,  UpdateAddonInputBoundary updateAddonInputBoundary){

        this.createAddonInputBoundary = createAddonInputBoundary;
        this.deleteAddonInputBoundary = deleteAddonInputBoundary;
        this.readAddonInputBoundary = readAddonInputBoundary;
        this.updateAddonInputBoundary = updateAddonInputBoundary;

    }

    public JSONObject runUpdateAddon(String input){

        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("addonID") && update_data.has("addonObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String addonId = update_data.getString("addonId");
        JSONObject addon = update_data.getJSONObject("addonObject");

        return this.updateAddonInputBoundary.updateAddon(vendorToken, addonId,addon);
    }

    public JSONObject runCreateAddon(String input){
        JSONObject create_data = new JSONObject(input);
        if(!(create_data.has("addonID") && create_data.has("addonObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_addon = create_data.getJSONObject("addonObject");


        return this.createAddonInputBoundary.createAddon(vendorToken,new_addon);
    }


    public JSONObject runDeleteAddon(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!(delete_data.has("addonID") && delete_data.has("vendorToken"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String addonId = delete_data.getString("addonId");


        return this.deleteAddonInputBoundary.deleteAddon(vendorToken,addonId);
    }

    public JSONObject runReadAddon(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("addonID")){

            //TODO:Call presenter with error message
        }
        String addonId = read_data.getString("addonId");


        return this.readAddonInputBoundary.readAddon(addonId);
    }


}
