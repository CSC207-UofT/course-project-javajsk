package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for CancelOrderInteractor
 */
public interface CancelOrder {
    /**
     * Method for setting order status as cancelled
     *
     * @param userToken the user token
     * @param orderId   the order id
     * @return a response object
     */
    ResponseObject cancelOrder(String userToken, String orderId);
}
