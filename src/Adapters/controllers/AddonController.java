package Adapters.controllers;

import UseCases.Orders.CreateOrderInputBoundary;
import businessrules.addon.inputboundaries.DeleteAddonInputBoundary;
import businessrules.addon.inputboundaries.ReadAddonInputBoundary;
import businessrules.addon.inputboundaries.UpdateAddonInputBoundary;
import org.json.JSONObject;

public class AddonController {
    CreateOrderInputBoundary createOrderInputBoundary;
    DeleteAddonInputBoundary deleteAddonInputBoundary;
    ReadAddonInputBoundary readAddonInputBoundary;
    UpdateAddonInputBoundary updateAddonInputBoundary;
    JSONParser jsonParser;

    public AddonController(CreateOrderInputBoundary createOrderInputBoundary,DeleteAddonInputBoundary deleteAddonInputBoundary,
                           ReadAddonInputBoundary readAddonInputBoundary,  UpdateAddonInputBoundary updateAddonInputBoundary,
                           JSONParser jsonParser){

        this.createOrderInputBoundary = createOrderInputBoundary;
        this.deleteAddonInputBoundary = deleteAddonInputBoundary;
        this.jsonParser = jsonParser;
        this.readAddonInputBoundary = readAddonInputBoundary;
        this.updateAddonInputBoundary = updateAddonInputBoundary;

    }

    public void runUpdateAddon(String input){
        JSONObject data = this.parser.parse(input);
        String vendorToken = data.getString("vendorToken");
        String addonId = data.getString("addonId");
        JSONObject object = data.getJSONObject("")


        this.updateAddonInputBoundary.updateAddon(vendorToken, addonId )
    }

}
