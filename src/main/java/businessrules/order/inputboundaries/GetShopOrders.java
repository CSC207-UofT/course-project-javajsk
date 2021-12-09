package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for GetShopOrdersInteractor
 */
public interface GetShopOrders {
    /**
     * Method for getting a shops orders
     *
     * @param vendorToken the vendor token
     * @return a response object
     */
    ResponseObject getShopOrders(String vendorToken);
}
