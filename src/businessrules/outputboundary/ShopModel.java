package businessrules.outputboundary;

import org.json.JSONObject;

public interface ShopModel {
    JSONObject displayShop(JSONObject shop);

    JSONObject displayError(String errorMessage);

}
