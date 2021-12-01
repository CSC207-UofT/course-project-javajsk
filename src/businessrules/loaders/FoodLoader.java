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
    FoodRepository foodRepository;

    public FoodLoader(FoodRepository foodRepository, ErrorModel errorHandler){
        this.errorHandler = errorHandler;
        this.foodRepository = foodRepository;
    }

    public static Food loadFood(JSONObject object) throws JSONException {
        String id = object.getString("id");
        String name = object.getString("name");
        float price = object.getFloat("price");
        String desc = object.getString("description");
        Singleton[] components = loadComponnets(object.getJSONArray("components"));
        return new Food(id, name, desc, price, components);
    }

    public static Singleton[] loadComponnets(JSONArray data){
        Singleton[] finalData = new Singleton[data.length()];
        for(int i =0; i < data.length(); i++){
            finalData[i] = SingletonLoader.loadSingleton(data.getJSONObject(i));
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
            return FoodLoader.loadFood(foodRaw);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
        }
        return null;
    }
}
