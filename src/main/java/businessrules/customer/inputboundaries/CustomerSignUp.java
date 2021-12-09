package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for CustomerSignUpInteractor
 */
public interface CustomerSignUp {
    /**
     * Method for signing up as a customer
     *
     * @param username     the new username
     * @param password     the new password
     * @param passwordConf the password confirmation
     * @return a response object
     */
    ResponseObject signUp(String username, String password, String passwordConf);
}
