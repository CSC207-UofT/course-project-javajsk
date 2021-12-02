package businessrules.shop.inputboundaries;

import org.json.JSONObject;

public interface UpdateShopInputBoundary {
    boolean updateShop(String vendorToken, String shopId, JSONObject shopData);
}
