package businessrules.customer.inputboundaries;

import org.json.JSONObject;

public interface UpdateCustomerInputBoundary {
    JSONObject updateCustomer(String userToken, JSONObject newData);
}
