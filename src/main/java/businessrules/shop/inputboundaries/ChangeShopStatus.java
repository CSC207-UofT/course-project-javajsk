package businessrules.shop.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface ChangeShopStatus {
    /**
     * Change the status of the shop belonging to the
     * specified vendor. Must only be used by a vendor.
     *
     * @param vendorToken the vendor that owns the shop
     * @param newStatus   the new status of the shop
     * @return            JSONobject representing the shop
     */
    ResponseObject changeShopStatus(String vendorToken, boolean newStatus);
}
