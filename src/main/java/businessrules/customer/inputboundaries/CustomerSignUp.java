package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface CustomerSignUp {
    ResponseObject login(String username, String password, String passwordConf);
}
