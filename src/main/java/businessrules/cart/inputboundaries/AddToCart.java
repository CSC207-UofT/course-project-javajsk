package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Selection;

/**
 * Input boundary for AddToCartInteractor
 */
public interface AddToCart {
    /**
     * Method that adds given food and selection to cart with given shop id
     *
     * Requires customer to be logged in (valid customer token)
     *
     * @param userToken token of customer currently logged in
     * @param shopId    shop id
     * @param foodId    food id
     * @param selection customer's selection (for customization)
     * @return response object containing cart or error message to display
     */
    ResponseObject addToCart(String userToken, String shopId,
                             String foodId, Selection[] selection);
}
