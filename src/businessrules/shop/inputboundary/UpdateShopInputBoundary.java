package businessrules.shop.inputboundary;

import org.json.JSONObject;

public interface UpdateShopInputBoundary {
    boolean updateShop(String vendorToken, String shopId, JSONObject shopData);
}
