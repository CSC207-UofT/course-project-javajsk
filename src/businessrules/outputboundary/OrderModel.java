package businessrules.outputboundary;

import org.json.JSONObject;

public interface OrderModel {
    JSONObject displayOrder(JSONObject order);

    JSONObject displayError(String errorMessage);
}
