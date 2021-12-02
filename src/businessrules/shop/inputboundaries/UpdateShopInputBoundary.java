package businessrules.shop.inputboundaries;

import org.json.JSONObject;

public interface UpdateShopInputBoundary {
    JSONObject updateShop(String vendorToken, String shopId, JSONObject shopData);
}
