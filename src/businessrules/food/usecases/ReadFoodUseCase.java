package businessrules.food.usecases;

import businessrules.dai.FoodRepository;
import businessrules.food.inputboundaries.ReadFoodInputBoundary;
import businessrules.loaders.FoodLoader;
import businessrules.outputboundary.FoodModel;
import businessrules.outputboundary.ErrorModel;
import entities.Food;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadFoodUseCase implements ReadFoodInputBoundary {
    FoodRepository FoodRepository;
    FoodModel FoodModel;
    ErrorModel errorHandler;

    @Override
    public JSONObject readFood(String id) {

        JSONObject data = FoodRepository.readFood(id);

        Food food;
        try {
            food = FoodLoader.loadFood(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return null;
        }
        FoodModel.displayFood(food.jsonify());
        return food.jsonify();
    }
}
