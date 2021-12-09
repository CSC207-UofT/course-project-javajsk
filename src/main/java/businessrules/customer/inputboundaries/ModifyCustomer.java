package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for ModifyCustomerInteractor
 */
public interface ModifyCustomer {
    /**
     * Method for modifying a customer
     *
     * @param userToken    the customer token
     * @param username     the customer username
     * @param password     the customer password
     * @param passwordConf the password confirmation
     * @return a response object
     */
    ResponseObject modify(String userToken, String username, String password, String passwordConf);
}
