package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface SetOrderInprogress {
    ResponseObject setOrderInprogress(String vendorToken, String orderId);
}
