package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Selection;

public interface AddToCart {
    ResponseObject addToCart(String userToken, String shopId,
                             String foodId, Selection[] selection);
}
