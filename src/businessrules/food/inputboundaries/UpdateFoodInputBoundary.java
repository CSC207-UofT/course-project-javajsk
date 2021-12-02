package businessrules.food.inputboundaries;

import org.json.JSONObject;

public interface UpdateFoodInputBoundary {
    JSONObject updateFood(String vendorToken, String foodId, JSONObject data);
}
