package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface ChangeShopStatus {
    ResponseObject changeShopStatus(String vendorToken, boolean newStatus);
}
