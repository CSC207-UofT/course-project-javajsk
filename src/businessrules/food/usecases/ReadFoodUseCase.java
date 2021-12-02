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
    FoodRepository foodRepository;
    FoodModel foodModel;
    FoodLoader foodLoader;
    ErrorModel errorHandler;

    public ReadFoodUseCase(FoodRepository foodRepo, FoodModel foodMod, FoodLoader foodLoad, ErrorModel error) {
        this.foodRepository = foodRepo;
        this.foodModel = foodMod;
        this.foodLoader = foodLoad;
        this.errorHandler = error;
    }

    @Override
    public JSONObject readFood(String id) {

        JSONObject data = foodRepository.readFood(id);

        Food food;
        try {
            food = foodLoader.loadFood(data);
        }catch (JSONException e){
            return foodModel.displayError(e.getMessage());
        }
        return foodModel.displayFood(food.jsonify());
    }
}
