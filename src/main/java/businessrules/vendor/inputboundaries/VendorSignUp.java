package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface VendorSignUp {
    ResponseObject signUp(String username, String password, String passwordConf,
                          String shopName, String shopLoc);
}
