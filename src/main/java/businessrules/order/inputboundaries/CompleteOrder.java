package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface CompleteOrder {
    ResponseObject completeOrder(String vendorToken, String orderId);
}
