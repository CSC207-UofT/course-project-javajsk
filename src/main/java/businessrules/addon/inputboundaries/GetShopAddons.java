package businessrules.addon.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary GetShopAddonsInteractor
 */
public interface GetShopAddons {
    /**
     * Method that returns a response object that contains the addons of the shop
     * with the given id
     *
     * @param shopId id of shop
     * @return response object containing shop addons or error message
     */
    ResponseObject getShopAddons(String shopId);
}
