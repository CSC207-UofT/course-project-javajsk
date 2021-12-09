package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for GetNextOrderInterator
 */
public interface GetNextOrder {
    /**
     * Method for getting the next incomplete order
     *
     * @param vendorToken the vendor token
     * @return a response object
     */
    ResponseObject getNextOrder(String vendorToken);
}
