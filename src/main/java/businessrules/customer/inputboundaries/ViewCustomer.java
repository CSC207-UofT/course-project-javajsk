package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for ViewCustomerInteractor
 */
public interface ViewCustomer {
    /**
     * Method that returns object containing customer information with given id to display
     *
     * @param customerId id of customer to display
     * @return response object containing customer or error message to display
     */
    ResponseObject viewCustomer(String customerId);
}
