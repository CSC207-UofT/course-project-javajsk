package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Selection;

public interface RemoveFromCart {
    ResponseObject removeFromCart(String userToken, Food food, Selection[] selections);
}
