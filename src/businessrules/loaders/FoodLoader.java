package businessrules.loaders;

import businessrules.dai.FoodRepository;
import businessrules.outputboundary.ErrorModel;
import entities.Food;
import entities.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoodLoader {
    ErrorModel errorHandler;
    SingletonLoader singletonLoader;
    FoodRepository foodRepository;
    FoodLoader foodLoader;

    public FoodLoader(FoodRepository foodRepository, SingletonLoader singletonLoader, ErrorModel errorHandler){
        this.errorHandler = errorHandler;
        this.singletonLoader = singletonLoader;
        this.foodRepository = foodRepository;
        this.foodLoader = new FoodLoader(this.foodRepository, this.singletonLoader, this.errorHandler);
    }

    public Food loadFood(JSONObject object) throws JSONException {
        String id = object.getString("id");
        String name = object.getString("name");
        float price = object.getFloat("price");
        String desc = object.getString("description");
        Singleton[] components = loadComponents(object.getJSONArray("components"));
        return new Food(id, name, desc, price, components);
    }

    public Singleton[] loadComponents(JSONArray data){
        Singleton[] finalData = new Singleton[data.length()];
        for(int i =0; i < data.length(); i++){
            finalData[i] = singletonLoader.loadSingleton(data.getJSONObject(i));
        }
        return finalData;
    }

    public Food loadFoodFromId(String foodId){
        JSONObject foodRaw = foodRepository.readFood(foodId);
        if(foodRaw == null){
            errorHandler.displayError("Unable to find food with id: " + foodId);
            return null;
        }

        try {
            return foodLoader.loadFood(foodRaw);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
        }
        return null;
    }
}
