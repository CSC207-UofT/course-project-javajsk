package businessrules.outputboundary;

import org.json.JSONObject;

public interface FoodModel {
    public JSONObject displayFood(JSONObject data);

    public JSONObject displayError(String error);


}
