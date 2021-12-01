package businessrules.customer.inputboundaries;

import org.json.JSONObject;

public interface UpdateCustomerInputBoundary {
    boolean updateCustomer(String userToken, JSONObject newData);
}
