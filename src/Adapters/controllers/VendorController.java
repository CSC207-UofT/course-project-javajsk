package Adapters.controllers;

import businessrules.vendor.inputboundaries.CreateVendorInputBoundary;
import businessrules.vendor.inputboundaries.DeleteVendorInputBoundary;
import businessrules.vendor.inputboundaries.ReadVendorInputBoundary;
import businessrules.vendor.inputboundaries.UpdateVendorInputBoundary;
import org.json.JSONObject;

public class VendorController {

    CreateVendorInputBoundary createVendorInputBoundary;
    DeleteVendorInputBoundary deleteVendorInputBoundary;
    UpdateVendorInputBoundary updateVendorInputBoundary;
    ReadVendorInputBoundary readVendorInputBoundary;

    public VendorController(CreateVendorInputBoundary createVendorInputBoundary,
                          DeleteVendorInputBoundary deleteVendorInputBoundary,
                          UpdateVendorInputBoundary updateVendorInputBoundary,
                          ReadVendorInputBoundary readVendorInputBoundary){
        this.createVendorInputBoundary = createVendorInputBoundary;
        this.deleteVendorInputBoundary = deleteVendorInputBoundary;
        this.readVendorInputBoundary = readVendorInputBoundary;
        this.updateVendorInputBoundary = updateVendorInputBoundary;
    }

    public void runUpdateVendor(String input){
        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("vendorId") &&
                update_data.has("vendorObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String vendorId = update_data.getString("vendorID");
        JSONObject vendorObject = update_data.getJSONObject("vendorObject");


        this.updateVendorInputBoundary.updateVendor(vendorToken, vendorId,vendorObject);
    }

    public void runCreateVendor(String input){
        JSONObject create_data = new JSONObject(input);
        if(!(create_data.has("vendorID") && create_data.has("vendorObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_vendor = create_data.getJSONObject("vendorObject");


        this.createVendorInputBoundary.createVendor(vendorToken,new_vendor);
    }


    public void runDeleteVendor(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!(delete_data.has("vendorID") && delete_data.has("vendorToken"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String vendorId = delete_data.getString("vendorId");


        this.deleteVendorInputBoundary.deleteVendor(vendorToken,vendorId);
    }

    public void runReadVendor(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("vendorId")){

            //TODO:Call presenter with error message
        }
        String vendorId = read_data.getString("vendorId");


        this.readVendorInputBoundary.readVendor(vendorId);
    }
}
