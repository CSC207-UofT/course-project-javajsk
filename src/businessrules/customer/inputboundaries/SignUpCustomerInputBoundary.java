package businessrules.customer.inputboundaries;

import org.json.JSONObject;

public interface SignUpCustomerInputBoundary {
    JSONObject signUp(JSONObject data);
}
