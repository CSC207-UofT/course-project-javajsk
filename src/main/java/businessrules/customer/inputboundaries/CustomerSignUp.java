package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface CustomerSignUp {
    ResponseObject signup(String username, String password, String passwordConf);
}
