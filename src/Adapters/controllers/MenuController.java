package Adapters.controllers;

import businessrules.menu.inputboundaries.ReadMenuInputBoundary;
import businessrules.menu.usecases.ClearMenuUseCase;
import org.json.JSONObject;

public class MenuController {

    ClearMenuUseCase clearMenuUseCase;
    ReadMenuInputBoundary readMenuInputBoundary;

    public MenuController(ClearMenuUseCase clearMenuUseCase,
                          ReadMenuInputBoundary readMenuInputBoundary){
        this.clearMenuUseCase = clearMenuUseCase;
        this.readMenuInputBoundary = readMenuInputBoundary;
    }


    public void runClearMenu(String input){
        JSONObject delete_data = new JSONObject(input);
        if(delete_data.has("vendorToken")){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");


        this.clearMenuUseCase.clearMenu(vendorToken);
    }

    public void runReadMenu(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("menuId")){

            //TODO:Call presenter with error message
        }
        String menuId = read_data.getString("menuId");


        this.readMenuInputBoundary.readMenu(menuId);
    }
}
