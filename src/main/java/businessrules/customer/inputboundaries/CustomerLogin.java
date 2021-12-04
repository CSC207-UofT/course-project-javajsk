package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface CustomerLogin {
    ResponseObject login(String username, String password);
}
