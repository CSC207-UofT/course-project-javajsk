package businessrules.food.inputboundaries;

import org.json.JSONObject;

public interface UpdateFoodInputBoundary {
    boolean updateFood(String vendorToken, String foodId, JSONObject data);
}
