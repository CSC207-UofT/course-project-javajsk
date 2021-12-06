package adapters.dam.entityrepoitories;

import adapters.dam.DBGateway;
import businessrules.dai.Repository;
import entities.Food;
import entities.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * An implementation of a repository with type food
 */
public class FoodDB implements Repository<Food> {
    DBGateway databaseConnector;
    final String tableName = "Food";

    /**
     * Instantiates a food database
     * @param databaseConnector the connector to the database
     */
    public FoodDB(DBGateway databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Method for reading a food entry from the database
     * @param id the id of the food entry
     * @return the food entity
     */
    @Override
    public Food read(String id) {
        System.out.println("ID:"+id);
        return loadFoodFromJSON(databaseConnector.read(tableName, id));
    }

    /**
     * Method for updating a food entry in the database
     * @param id the id of the food entry
     * @param item the updated food information
     * @return whether the update was successful
     */
    @Override
    public boolean update(String id, Food item) {
        return databaseConnector.update(tableName, id, loadJSONFromFood(item));

    }


    /**
     * Method for adding a new food entry to the database
     * @param item the new food information
     * @return the id of the new food entry
     */
    @Override
    public String create(Food item) {
        return databaseConnector.create(tableName, loadJSONFromFood(item));
    }

    /**
     * Method for retrieving multiple food entries from the database
     * @param parameter the parameter to search by
     * @param needle the value of the parameter to search by
     * @return a list of food entities that match the needle
     */
    @Override
    public List<Food> readMultiple(String parameter, String needle) {
        List<Food> foodList = new ArrayList<>();
        List<JSONObject> rawFoods = databaseConnector.readMultiple(tableName, parameter, needle);
        for(JSONObject rawFood: rawFoods){
            foodList.add(loadFoodFromJSON(rawFood));
        }
        return foodList;
    }


    /**
     * Method for finding a food entry in the database
     * @param fieldName the field to search by
     * @param needle the value of the field to search
     * @return a food entity that matches the requirements
     */
    @Override
    public Food findOneByFieldName(String fieldName, String needle) {
        return loadFoodFromJSON(databaseConnector.readOne(tableName,fieldName,needle));
    }

    /**
     * Method for converting a food entity to a JSON object
     * @param food the food entity
     * @return the corresponding JSON object
     */
    public static JSONObject loadJSONFromFood(Food food){
        return new JSONObject(food.toString());

    }

    /**
     * Method for creating a food entity from a JSON object
     * @param rawData the food data
     * @return the corresponding food entity
     */
    public Food loadFoodFromJSON(JSONObject rawData){
        SingletonDB singletonLoader = new SingletonDB(databaseConnector);
        try{
            String id = rawData.getString("id");
            String name = rawData.getString("name");
            String desc = rawData.getString("description");
            float price = rawData.getFloat("price");
            JSONArray arr = rawData.getJSONArray("components");
            Singleton[] selArr = new Singleton[arr.length()];
            for(int i =0;i <arr.length();i++){
                selArr[i] = singletonLoader.read(arr.getString(i));
            }
            String shopId = rawData.getString("shopId");
            return new Food(id,name, desc,price,selArr, shopId);
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
