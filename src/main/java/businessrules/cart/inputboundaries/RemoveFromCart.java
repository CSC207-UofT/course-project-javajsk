package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import entities.Food;
import entities.Selection;

public interface RemoveFromCart {
    ResponseObject removeFromCart(String userToken, Food food, Selection[] selections);
}
