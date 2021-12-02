package businessrules.food.inputboundaries;

import org.json.JSONObject;

public interface DeleteFoodInputBoundary {
    JSONObject deleteFood(String vendorToken, String id);
}
