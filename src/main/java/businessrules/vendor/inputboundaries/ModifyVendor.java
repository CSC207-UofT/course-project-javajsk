package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface ModifyVendor {
    ResponseObject modifyVendor(String vendorToken, String username, String password, String passwordConf);
}
