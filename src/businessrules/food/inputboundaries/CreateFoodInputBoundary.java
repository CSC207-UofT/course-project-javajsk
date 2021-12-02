package businessrules.food.inputboundaries;

import org.json.JSONObject;

public interface CreateFoodInputBoundary {
    JSONObject createFood(String vendorToken, JSONObject data);
}
