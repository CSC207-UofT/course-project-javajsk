package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for CompleteOrderInteractor
 */
public interface CompleteOrder {
    /**
     * Method for setting tha status of an order to completed
     *
     * @param vendorToken the vendor token
     * @param orderId     the order id
     * @return a response object
     */
    ResponseObject completeOrder(String vendorToken, String orderId);
}
