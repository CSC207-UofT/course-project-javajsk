package businessrules.dai;

import org.json.JSONObject;

public interface ShopRepository {
    String createShop(JSONObject data);

    boolean updateShop(String shopId, JSONObject shop);

    JSONObject readShop(String shopId);

    boolean deleteShop(String shopId);
}
