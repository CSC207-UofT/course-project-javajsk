package businessrules.shop.inputboundaries;

import org.json.JSONObject;

public interface CreateShopInputBoundary {
    JSONObject createShop(String vendorToken, JSONObject data);
}
