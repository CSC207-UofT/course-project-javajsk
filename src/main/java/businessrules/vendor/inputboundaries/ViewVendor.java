package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for ViewVendorInteractor
 */
public interface ViewVendor {
    /**
     * Method to display vendor with given id
     *
     * (or en error message if no such vendor exists)
     * @param vendorId id of vendor
     * @return response object containing vendor or error message
     */
    ResponseObject viewVendor(String vendorId);
}
