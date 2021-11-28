package Adapters.Controllers;

import Entities.FoodTruck;
import Entities.Interfaces.IOrderbook;
import Entities.Menu;
import UseCases.FoodTruck.*;
import UseCases.FoodTruck.*;
import org.json.JSONObject

public class FoodTruckController {
    ChangeMenuInputBoundary changeMenuInputBoundary;
    CreateFoodTruckInputBoundary createFoodTruckInputBoundary;
    GetMenuInputBoundary getMenuInputBoundary;
    ProcessOrderInputBoundary processOrderInputBoundary;

    public FoodTruckController(ChangeMenuInputBoundary changeMenuInputBoundary,CreateFoodTruckInputBoundary createFoodTruckInputBoundary,
                               GetMenuInputBoundary getMenuInputBoundary,ProcessOrderInputBoundary processOrderInputBoundary ){
        this.changeMenuInputBoundary = changeMenuInputBoundary;
        this.createFoodTruckInputBoundary = createFoodTruckInputBoundary;
        this.getMenuInputBoundary = getMenuInputBoundary;
        this.processOrderInputBoundary = processOrderInputBoundary;
    }

    //TODO: Parse JSON from string
    public void runChangeMenu(JSONObject input){
        String userToken = input.getString("userToken");
        String ShopId = input.getString("shopId");
        String Menu = input.getString("Menu");

        this.changeMenuInputBoundary.changeMenu(userToken, ShopId);
    }
    public void runCreateFoodTruck(String userToken, String name, Menu menu, String status,
                                   IOrderbook orderbook, String location){


    }
    public void runGetMenu(String userToken, String shopId){

    }
    public void runProcessOrder(String userToken, String shopId){

    }
}
