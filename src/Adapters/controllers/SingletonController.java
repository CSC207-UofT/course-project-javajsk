package Adapters.controllers;

import businessrules.singleton.inputboundaries.CreateSingletonInputBoundary;
import businessrules.singleton.inputboundaries.DeleteSingletonInputBoundary;
import businessrules.singleton.inputboundaries.ReadSingletonInputBoundary;
import businessrules.singleton.inputboundaries.UpdateSingletonInputBoundary;
import org.json.JSONObject;

public class SingletonController {

    CreateSingletonInputBoundary createSingletonInputBoundary;
    DeleteSingletonInputBoundary deleteSingletonInputBoundary;
    UpdateSingletonInputBoundary updateSingletonInputBoundary;
    ReadSingletonInputBoundary readSingletonInputBoundary;

    public SingletonController(CreateSingletonInputBoundary createSingletonInputBoundary,
                          DeleteSingletonInputBoundary deleteSingletonInputBoundary,
                          UpdateSingletonInputBoundary updateSingletonInputBoundary,
                          ReadSingletonInputBoundary readSingletonInputBoundary){
        this.createSingletonInputBoundary = createSingletonInputBoundary;
        this.deleteSingletonInputBoundary = deleteSingletonInputBoundary;
        this.readSingletonInputBoundary = readSingletonInputBoundary;
        this.updateSingletonInputBoundary = updateSingletonInputBoundary;
    }

    public void runUpdateSingleton(String input){
        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("singletonId") &&
                update_data.has("singletonObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String singletonId = update_data.getString("singletonID");
        JSONObject singletonObject = update_data.getJSONObject("singletonObject");


        this.updateSingletonInputBoundary.updateSingleton(vendorToken, singletonId,singletonObject);
    }

    public void runCreateSingleton(String input){
        JSONObject create_data = new JSONObject(input);
        if(!(create_data.has("singletonID") && create_data.has("singletonObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_singleton = create_data.getJSONObject("singletonObject");


        this.createSingletonInputBoundary.createSingleton(vendorToken,new_singleton);
    }


    public void runDeleteSingleton(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!(delete_data.has("singletonID") && delete_data.has("vendorToken"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String singletonId = delete_data.getString("singletonId");


        this.deleteSingletonInputBoundary.deleteSingleton(vendorToken,singletonId);
    }

    public void runReadSingleton(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("singletonId")){

            //TODO:Call presenter with error message
        }
        String singletonId = read_data.getString("singletonId");


        this.readSingletonInputBoundary.readSingleton(singletonId);
    }
}
