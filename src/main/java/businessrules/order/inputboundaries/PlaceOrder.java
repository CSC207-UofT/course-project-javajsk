package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for PlaceOrderInteractor
 */
public interface PlaceOrder {
    /**
     * Method for placing an order
     *
     * @param userToken the customer token
     * @return a response object
     */
    ResponseObject placeOrder(String userToken);
}
