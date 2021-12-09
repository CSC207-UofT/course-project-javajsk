package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for ViewShopInteractor
 */
public interface ViewShop {
    /**
     * Method returns a response object containing shop with shopId or error message
     *
     * @param shopId id of shop
     * @return response object
     */
    ResponseObject viewShop(String shopId);
}


