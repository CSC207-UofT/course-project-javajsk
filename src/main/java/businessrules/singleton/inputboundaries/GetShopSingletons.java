package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for GetShopSingletons
 */
public interface GetShopSingletons {
    /**
     * Returns all Singletons in the specified shop.
     *
     * @param shopId id of the shop
     * @return response object
     */
    ResponseObject getShopSingletons(String shopId);
}
