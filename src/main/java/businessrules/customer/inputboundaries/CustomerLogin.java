package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for CustomerLoginInteractor
 */
public interface CustomerLogin {
    /**
     * Method for logging in customer
     *
     * @param username the customer username
     * @param password the customer password
     * @return a response object
     */
    ResponseObject login(String username, String password);
}
