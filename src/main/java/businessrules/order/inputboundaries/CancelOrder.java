package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface CancelOrder {
    ResponseObject cancelOrder(String userToken, String orderId);
}
