package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Shop;

/**
 * Input boundary for ModifyShopInteractor
 */
public interface ModifyShop {
    /**
     * Modifies the specified Vendor's shop by replacing with another
     *
     * @param vendorToken the Vendor that owns the shop
     * @param shop        the Shop that will replace the original shop
     * @return a response object
     */
    ResponseObject modifyShop(String vendorToken, Shop shop);
}
