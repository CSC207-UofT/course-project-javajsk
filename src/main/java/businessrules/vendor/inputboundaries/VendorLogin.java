package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface VendorLogin {
    /**
     * Method that logs in a Vendor
     *
     * @param username username of the Vendor
     * @param password password of the Vendor
     * @return         the token of the Vendor
     */
    ResponseObject login(String username, String password);
}
