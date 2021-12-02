package businessrules.outputboundary;

import org.json.JSONObject;

public interface CustomerModel {
    JSONObject displayCustomer(JSONObject object);

    JSONObject displayError(String errorMessage);
}
