package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface ViewCart {
    ResponseObject viewCart(String userToken);
}
