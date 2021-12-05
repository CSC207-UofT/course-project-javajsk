package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Shop;

public interface ModifyShop {
    /**
     * Modifies the specified Vendor's shop by replacing with another
     *
     * @param vendorToken the Vendor that owns the shop
     * @param shop        the Shop that will replace the original shop
     * @return            JSONObject representing the newly modified shop
     */
    ResponseObject modifyShop(String vendorToken, Shop shop);
}
