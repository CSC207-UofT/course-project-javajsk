package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for VendorLoginInteractor
 */
public interface VendorLogin {
    /**
     * Method that logs in a Vendor
     *
     * @param username username of the Vendor
     * @param password password of the Vendor
     * @return response object containing the token of the Vendor or an error message
     */
    ResponseObject login(String username, String password);
}
