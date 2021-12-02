package businessrules.shop.inputboundaries;

import org.json.JSONObject;

public interface CreateShopInputBoundary {
    boolean createShop(String vendorToken, JSONObject data);
}
