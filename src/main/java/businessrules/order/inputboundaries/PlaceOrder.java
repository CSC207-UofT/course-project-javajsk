package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface PlaceOrder {
    ResponseObject placeOrder(String userToken);
}
