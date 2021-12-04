package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetNextOrder {
    ResponseObject getNextOrder(String vendorToken);
}
