package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input Boundary for VendorSignUpInteractor
 */
public interface VendorSignUp {
    /**
     * Method that creates a new Vendor
     *
     * @param username     username of the new Vendor
     * @param password     password of the new Vendor
     * @param passwordConf password of the new Vendor confirmed
     * @param shopName     name of the shop of the new Vendor
     * @param shopLocation location of the shop of the new Vendor
     * @return response object representing the new Vendor (or error message)
     */
    ResponseObject signUp(String username, String password, String passwordConf,
                          String shopName, String shopLocation);
}
