package businessrules.customer.inputboundaries;

import org.json.JSONObject;

public interface SignUpCustomerInputBoundary {
    boolean signUp(JSONObject data);
}
