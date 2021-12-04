package businessrules.customer.inputboundaries;

import businessrules.outputboundaries.ResponseObject;

public interface ModifyCustomer {
    ResponseObject modify(String userToken, String username, String password, String passwordConf);
}
