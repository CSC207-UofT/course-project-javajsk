package Adapters.controllers;

import businessrules.food.inputboundaries.CreateFoodInputBoundary;
import businessrules.food.inputboundaries.DeleteFoodInputBoundary;
import businessrules.food.inputboundaries.ReadFoodInputBoundary;
import businessrules.food.inputboundaries.UpdateFoodInputBoundary;
import org.json.JSONObject;

public class FoodController {

    CreateFoodInputBoundary createFoodInputBoundary;
    DeleteFoodInputBoundary deleteFoodInputBoundary;
    UpdateFoodInputBoundary updateFoodInputBoundary;
    ReadFoodInputBoundary readFoodInputBoundary;

    public FoodController(CreateFoodInputBoundary createFoodInputBoundary,
            DeleteFoodInputBoundary deleteFoodInputBoundary,
            UpdateFoodInputBoundary updateFoodInputBoundary,
            ReadFoodInputBoundary readFoodInputBoundary){
        this.createFoodInputBoundary = createFoodInputBoundary;
        this.deleteFoodInputBoundary = deleteFoodInputBoundary;
        this.readFoodInputBoundary = readFoodInputBoundary;
        this.updateFoodInputBoundary = updateFoodInputBoundary;
    }

    public void runUpdateFood(String input){
        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("foodId") &&
                update_data.has("foodObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String foodId = update_data.getString("foodID");
        JSONObject foodObject = update_data.getJSONObject("foodObject");


        this.updateFoodInputBoundary.updateFood(vendorToken, foodId,foodObject);
    }

    public void runCreateFood(String input){
        JSONObject create_data = new JSONObject(input);
        if(!create_data.has("foodID") && create_data.has("foodObject")){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_food = create_data.getJSONObject("foodObject");


        this.createFoodInputBoundary.createFood(vendorToken,new_food);
    }


    public void runDeleteFood(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!delete_data.has("foodID") && delete_data.has("vendorToken")){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String foodId = delete_data.getString("foodId");


        this.deleteFoodInputBoundary.deleteFood(vendorToken,foodId);
    }

    public void runReadFood(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("foodId")){

            //TODO:Call presenter with error message
        }
        String foodId = read_data.getString("foodId");


        this.readFoodInputBoundary.readFood(foodId);
    }
}
