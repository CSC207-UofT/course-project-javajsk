package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for GetUserPastOrdersInteractor
 */
public interface GetUserPastOrders {
    /**
     * Method for getting a customer's past orders
     *
     * @param userToken customer token
     * @return a response object
     */
    ResponseObject getUserPastOrders(String userToken);
}
