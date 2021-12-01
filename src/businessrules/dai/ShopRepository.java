package businessrules.dai;

import org.json.JSONObject;

public interface ShopRepository {
    String createShop(JSONObject data);

    boolean updateShop(String shopId, JSONObject shop);

    boolean clearShopMenu(String shopId);

    JSONObject readShop(String shopId);

    JSONObject readShopMenu(String shopId);

    boolean deleteShop(String shopId);
}
