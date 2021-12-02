package businessrules.outputboundary;

import org.json.JSONObject;

public interface ShopModel {
    public JSONObject displayShop(JSONObject data);

    public JSONObject displayError(String error);


}
