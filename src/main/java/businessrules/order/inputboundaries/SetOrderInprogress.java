package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for SetOrderInprogressInteractor
 */
public interface SetOrderInprogress {
    /**
     * Method for setting the order status as in progress
     *
     * @param vendorToken the vendor token
     * @param orderId     the order id
     * @return a response object
     */
    ResponseObject setOrderInprogress(String vendorToken, String orderId);
}
