package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for EmptyCartInteractor
 */
public interface EmptyCart {
    /**
     * Method empties/clears the cart of the customer with given token
     * and returns object containing information to display
     * @param userToken token of customer currently logged in
     * @return response object containing empty cart or error message to display
     */
    ResponseObject emptyCart(String userToken);
}
