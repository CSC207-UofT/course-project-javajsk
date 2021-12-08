package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for ViewCartInteractor
 */
public interface ViewCart {
    /**
     * Method that returns information to display the cart of the customer
     * with the given token
     * @param userToken token of customer that is currently logged in
     * @return response object containing customer's cart or error message to display
     */
    ResponseObject viewCart(String userToken);
}
