package businessrules.dai;

import org.json.JSONObject;

public interface FoodRepository {
    // This function returns a string.
    String createFood(JSONObject data);

    boolean updateFood(String id, JSONObject data);

    boolean deleteFood(String id);

    JSONObject readFood(String id);
}
