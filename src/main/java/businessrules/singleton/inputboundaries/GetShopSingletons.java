package businessrules.singleton.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetShopSingletons {
    /**
     * Returns all Singletons in the specified shop.
     *
     * @param shopId id of the shop
     */
    ResponseObject getShopSingletons(String shopId);
}
