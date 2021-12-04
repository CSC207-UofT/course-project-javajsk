package businessrules.vendor.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface VendorLogin {
    ResponseObject login(String username, String password);
}
