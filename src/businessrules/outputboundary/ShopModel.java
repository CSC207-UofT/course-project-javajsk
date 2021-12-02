package businessrules.outputboundary;

import org.json.JSONObject;

public interface ShopModel {
    void displayShop(JSONObject shop);

    void updateShop(String id, JSONObject shop);

    void deleteShop(String id);
}
