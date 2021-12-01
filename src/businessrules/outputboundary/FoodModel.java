package businessrules.outputboundary;

import org.json.JSONObject;

public interface FoodModel {
    public void displayFood(JSONObject data);

    public void deleteFood(String foodId);

    public void updateFood(String foodId, JSONObject food);
}
