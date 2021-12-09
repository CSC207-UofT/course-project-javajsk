package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

/**
 * Input boundary for ModifyVendorInteractor
 */
public interface ModifyVendor {
    /**
     * Method that modifies a Vendor. Should only be called
     * by a Vendor to modify itself.
     *
     * @param vendorToken  token of the Vendor being modified
     * @param username     new username of the Vendor
     * @param password     new password of the Vendor
     * @param passwordConf new password of the Vendor confirmed
     * @return Response object
     */
    ResponseObject modifyVendor(String vendorToken, String username, String password, String passwordConf);
}
