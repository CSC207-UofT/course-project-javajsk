package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodDB implements Repository<Food> {
    DBGateway databaseConnector;
    final String tableName = "Food";

    public FoodDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Food read(String id) {
        return loadFoodFromJSON(databaseConnector.read(tableName, id));
    }

    @Override
    public boolean update(String id, Food item) {
        return databaseConnector.update(tableName, id, loadJSONFromFood(item));

    }


    @Override
    public String create(Food item) {
        return databaseConnector.create(tableName, loadJSONFromFood(item));
    }

    @Override
    public List<Food> readMultiple(String parameter, String needle) {
        List<Food> foodList = new ArrayList<>();
        List<JSONObject> rawFoods = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawFood: rawFoods){
            foodList.add(loadFoodFromJSON(rawFood));
        }
        return foodList;
    }


    @Override
    public Food findOneByFieldName(String fieldName, String needle) {
        return loadFoodFromJSON(databaseConnector.readOne(tableName,fieldName,needle));
    }

    public static JSONObject loadJSONFromFood(Food food){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", food.getId());
        jsonObject.put("name", food.getName());
        jsonObject.put("description", food.getDescription());
        jsonObject.put("price", food.getPrice());
        JSONArray arr = new JSONArray();
        for(Singleton sel: food.getComponents()){
            arr.put(SingletonDB.loadJSONFromSingleton(sel));
        }
        jsonObject.put("components", arr);
        return jsonObject;
    }

    public Food loadFoodFromJSON(JSONObject rawData){
        SingletonDB singletonLoader = new SingletonDB(databaseConnector);
        try{
            String id = rawData.getString("id");
            String name = rawData.getString("name");
            String desc = rawData.getString("description");
            Float price = rawData.getFloat("price");
            JSONArray arr = rawData.getJSONArray("components");
            Singleton[] selArr = new Singleton[arr.length()];
            for(int i =0;i <arr.length();i++){
                selArr[i] = singletonLoader.loadSingletonFromJSON(arr.getJSONObject(i));
            }
            String shopId = rawData.getString("shopId");
            return new Food(id,name, desc,price,selArr, shopId);
        }catch(JSONException e){
            return null;
        }
    }
}
