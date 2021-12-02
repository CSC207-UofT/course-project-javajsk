package businessrules.outputboundary;

import org.json.JSONObject;

public interface CustomerModel {
    void displayCustomer(JSONObject object);

    void updateCustomer(JSONObject object);
}
