package businessrules.food.inputboundaries;

import org.json.JSONObject;

public interface CreateFoodInputBoundary {
    boolean createFood(String vendorToken, JSONObject data);
}
