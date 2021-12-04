package businessrules.cart.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface EmptyCart {
    ResponseObject emptyCart(String userToken);
}
