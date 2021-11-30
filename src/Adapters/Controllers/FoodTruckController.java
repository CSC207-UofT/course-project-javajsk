package Adapters.Controllers;

import Adapters.JSONParser;
import Entities.FoodTruck;
import Entities.Interfaces.IFood;
import Entities.Interfaces.IOrderbook;
import Entities.Menu;
import UseCases.FoodTruck.*;
import UseCases.FoodTruck.*;
import org.json.JSONObject;

import java.util.HashMap;

public class FoodTruckController {
    CreateFoodTruckInputBoundary createFoodTruckInputBoundary;
    GetMenuInputBoundary getMenuInputBoundary;
    ProcessOrderInputBoundary processOrderInputBoundary;
    JSONParser parser;

    public FoodTruckController(CreateFoodTruckInputBoundary createFoodTruckInputBoundary,
                               GetMenuInputBoundary getMenuInputBoundary,ProcessOrderInputBoundary processOrderInputBoundary ){
        this.createFoodTruckInputBoundary = createFoodTruckInputBoundary;
        this.getMenuInputBoundary = getMenuInputBoundary;
        this.processOrderInputBoundary = processOrderInputBoundary;
    }

    public void runCreateFoodTruck(String raw_input){
        JSONObject input = parser.parse(raw_input);
        String userToken = input.getString("userToken");
        String ShopId = input.getString("shopId");
        String name = input.getString("name");
        JSONObject Menu = input.getJSONObject("Menu");
        //TODO: how to manage hashmaps being passed into JSON?
        //HashMap<IFood, Object[]> menu = Menu.get("menu");

    }
    public void runGetMenu(String raw_input){
        JSONObject input = parser.parse(raw_input);
        String userToken = input.getString("userToken");
        String ShopId = input.getString("shopId");
        this.getMenuInputBoundary.getMenu(userToken, ShopId);


    }
    public void runProcessOrder(String raw_input){
        JSONObject input = parser.parse(raw_input);
        String userToken = input.getString("userToken");
        String ShopId = input.getString("shopId");
        this.processOrderInputBoundary.processOrder(userToken, ShopId);

    }
}
