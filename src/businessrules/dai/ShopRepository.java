package businessrules.dai;

import org.json.JSONObject;

public interface ShopRepository {
    boolean updateShop(String shopId, JSONObject shop);

    boolean clearShopMenu(String shopId);
}
