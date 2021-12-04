package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface CustomerSignUp {
    ResponseObject signUp(String username, String password, String passwordConf);
}
