package businessrules.food.inputboundaries;

import org.json.JSONObject;

public interface ReadFoodInputBoundary {
    JSONObject readFood(String foodId);
}
