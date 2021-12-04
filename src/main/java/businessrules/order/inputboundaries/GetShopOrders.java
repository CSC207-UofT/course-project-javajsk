package businessrules.order.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface GetShopOrders {
    ResponseObject getShopOrders(String vendorToken);
}
