package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;
import entities.Selection;

/**
 * Input boundary for RemoveFromCartInteractor
 */
public interface RemoveFromCart {
    /**
     * Method removes given selection for given food from the customer's cart that
     * corresponds to the given token
     *
     * (if customer has multiple instances of same food in their cart, the one with
     * the given selection is removed - if there are multiple with this selection, the first
     * instance is removed)
     *
     * @param userToken  token of customer currently logged in
     * @param food       food to remove from cart
     * @param selections selection corresponding to food to remove from cart
     * @return response object containing updated cart or error message to display
     */
    ResponseObject removeFromCart(String userToken, Food food, Selection[] selections);
}
