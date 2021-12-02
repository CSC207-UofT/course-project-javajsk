package businessrules.shop.inputboundaries;

import org.json.JSONObject;

public interface DeleteShopInputBoundary {
    JSONObject deleteShop(String vendorToken, String shopId);
}
